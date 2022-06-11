import React, { Component } from "react";
import { InboxOutlined } from '@ant-design/icons';
import { Scatter } from '@ant-design/plots';
import { Button, Col, Descriptions, message, Row, Upload } from 'antd';
import pcapService from "../services/pcap.service";
const { Dragger } = Upload;

export default class TestEx extends Component {
  constructor(props) {
    super(props);
    this.onChange = this.onChange.bind(this);
    this.onDrop = this.onDrop.bind(this);
    this.reload = this.reload.bind(this);

    this.state = {
      submitted: false,
      packet_list: [],
      mcc: '',
      mnc: '',
      date: '',
    };
  }

  componentDidMount = () => {
    pcapService.getAll()
      .then((response) => {
        if (response.data !== '') {
          let packets = response.data;
          let mcc = null;
          let mnc = null;
          let date = null;

          for (let index = 0; index < packets.length; index++) {
            let packet = packets[index];
            if (packet.mcc !== '')
              mcc = packet.mcc;
            if (packet.mnc !== '')
              mnc = packet.mnc;
            if (mcc !== null && mnc !== null) {
              date = packet.date;
              break;
            }
          }
          console.log("exists data: " + mcc);
          this.setState({
            packet_list: response.data,
            submitted: true,
            mcc: mcc,
            mnc: mnc,
            date: date,
          });
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }

  onChange = (info) => {
    const { status } = info.file;

    if (status !== 'uploading') {
      console.log(info.file, info.fileList);
    }

    if (status === 'done') {
      message.success(`${info.file.name} file uploaded successfully.`);

      pcapService.getAll()
        .then((response) => {
          if (response.data !== []) {
            let packets = response.data;
            let mcc = null;
            let mnc = null;
            let date = null;

            for (let index = 0; index < packets.length; index++) {
              let packet = packets[index];
              if (packet.mcc !== '')
                mcc = packet.mcc;
              if (packet.mnc !== '')
                mnc = packet.mnc;
              if (mcc !== null && mnc !== null) {
                date = packet.date;
                break;
              }
            }
            console.log("exists data: " + mcc);
            this.setState({
              packet_list: response.data,
              submitted: true,
              mcc: mcc,
              mnc: mnc,
              date: date,
            });
          }
        })
        .catch((e) => {
          console.log(e);
        });

      this.setState({
        submitted: true
      });
    } else if (status === 'error') {
      message.error(`${info.file.name} file upload failed.`);
    }
  }

  onDrop = (e) => {
    console.log('Dropped files', e.dataTransfer.files);
  }

  reload = () => {
    pcapService.deleteAll();
    this.setState({
      submitted:false,
    })
    window.location.reload();
  }


  render() {
    return (
      <div>
        {this.state.submitted ?
          (
            <div>
              <Row justify="space-between" align="middle">
                <Col span={18}>
                  <Descriptions size="small" bordered>
                    <Descriptions.Item label="Date">{this.state.date}</Descriptions.Item>
                    <Descriptions.Item label="MCC">{this.state.mcc}</Descriptions.Item>
                    <Descriptions.Item label="MNC">{this.state.mnc}</Descriptions.Item>
                  </Descriptions>
                </Col>
                <Col span={2}>
                  <Button type="primary" size="middle" onClick={this.reload} danger>
                    Restart
                  </Button>
                </Col>
              </Row>
              <br />
              <br />
              <Scatter
                data={this.state.packet_list}
                padding="auto"
                xField="time"
                yField="captureSize"
                colorField='protocol'
                size={3}
                shape="circle"
                color={({ protocol }) => {
                  if (protocol === 'LTE') {
                    return 'green';
                  }
                  if (protocol === 'RRC') {
                    return 'orange';
                  }
                  if (protocol === 'GSM') {
                    return 'pink';
                  }
                }}
                pointStyle={({ x, y, protocol }) => {
                  if (protocol === 'LTE') {
                    return {
                      stroke: 'black',
                      strokeOpacity: 0.1,
                    }
                  }
                  if (protocol === 'RRC') {
                    return {
                      stroke: 'black',
                      strokeOpacity: 0.1,
                    }
                  }
                  if (protocol === 'GSM') {
                    return {
                      stroke: 'black',
                      strokeOpacity: 0.1,
                    }
                  }
                  return {
                    lineDash: 100,
                  }
                }}
                // seriesField="protocol"
                // xAxis={{tickCount: 9,}}

                tooltip={{
                  customContent: (title, items) => {
                    // const element = JSON.parse(JSON.stringify(items.at(0)));  //TMD
                    const element = Object.assign({}, items[0]);
                    // console.log("the context of element is " + element);
                    return (
                      <>
                        <div style={
                          {
                            width: 150,
                            // height: 132,
                            padding: 10,
                            backgroundColor: 'rgba(0, 0, 0, 0.75)',
                            color: 'white',
                            fontSize: 12,
                            margin: '0 -12px 0 -12px',
                            borderRadius: 2,
                          }}>
                          <h5 style={{ marginTop: 0, color: 'white' }}>Frame {element.data?.indice}</h5>
                          <ul style={{ paddingLeft: 0 }}>
                            <li
                              key={element.InboxOutlined}
                              className="g2-tooltip-list-item"
                              style={{ marginBottom: 4, display: 'flex', alignItems: 'center' }}
                            >
                              <span className="g2-tooltip-marker" style={{ backgroundColor: '#5D7092' }}></span>
                              <span style={{ display: 'inline-flex', flex: 1, justifyContent: 'space-between' }}>
                                <span style={{ margiRight: 16 }}>time:</span>
                                <span className="g2-tooltip-list-item-value">{element.data?.time}</span>
                              </span>
                            </li>

                            <li
                              key={element.InboxOutlined}
                              className="g2-tooltip-list-item"
                              style={{ marginBottom: 4, display: 'flex', alignItems: 'center' }}
                            >
                              <span className="g2-tooltip-marker" style={{ backgroundColor: '#5D7092' }}></span>
                              <span style={{ display: 'inline-flex', flex: 1, justifyContent: 'space-between' }}>
                                <span style={{ margiRight: 16 }}>src:</span>
                                <span className="g2-tooltip-list-item-value">{element.data?.src}</span>
                              </span>
                            </li>

                            <li
                              key={element.InboxOutlined}
                              className="g2-tooltip-list-item"
                              style={{ marginBottom: 4, display: 'flex', alignItems: 'center' }}
                            >
                              <span className="g2-tooltip-marker" style={{ backgroundColor: '#5D7092' }}></span>
                              <span style={{ display: 'inline-flex', flex: 1, justifyContent: 'space-between' }}>
                                <span style={{ margiRight: 16 }}>dst:</span>
                                <span className="g2-tooltip-list-item-value">{element.data?.dst}</span>
                              </span>
                            </li>

                            <li
                              key={element.InboxOutlined}
                              className="g2-tooltip-list-item"
                              style={{ marginBottom: 4, display: 'flex', alignItems: 'center' }}
                            >
                              <span className="g2-tooltip-marker" style={{ backgroundColor: '#5D7092' }}></span>
                              <span style={{ display: 'inline-flex', flex: 1, justifyContent: 'space-between' }}>
                                <span style={{ margiRight: 16 }}>size:</span>
                                <span className="g2-tooltip-list-item-value">{element.data?.captureSize} bits</span>
                              </span>
                            </li>

                            <li
                              key={element.InboxOutlined}
                              className="g2-tooltip-list-item"
                              style={{ marginBottom: 4, display: 'flex', alignItems: 'center' }}
                            >
                              <span className="g2-tooltip-marker" style={{ backgroundColor: '#5D7092' }}></span>
                              <span style={{ display: 'inline-flex', flex: 1, justifyContent: 'space-between' }}>
                                <span style={{ margiRight: 16 }}>channel:</span>
                                <span className="g2-tooltip-list-item-value">{element.data?.channel}</span>
                              </span>
                            </li>
                          </ul>
                        </div>
                      </>
                    );
                  },
                }}
              />
            </div>
          ) :
          (
            <Dragger name="file" multiple={true} action="http://localhost:8080/api/pcapData"
              onChange={this.onChange} onDrop={this.onDrop}>
              <p className="ant-upload-drag-icon">
                <InboxOutlined />
              </p>
              <p className="ant-upload-text">Click or drag file to this area to upload</p>
              <p className="ant-upload-hint">
                Support for a single upload.
              </p>
            </Dragger>
          )
        }
      </div>
    );
  }
}