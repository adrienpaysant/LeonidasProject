import React, { Component } from "react";
import { InboxOutlined } from '@ant-design/icons';
import { message, Upload, Card  } from 'antd';
import pcapService from "../services/pcap.service";
const { Dragger } = Upload;

export default class TestEx extends Component {
  constructor(props) {
    super(props);
    this.onChange = this.onChange.bind(this);
    this.onDrop = this.onDrop.bind(this);

    this.state = {
      submitted: false,
      packet_list: [],
    };
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
          // console.log(response.data);
          this.setState({
            packet_list: response.data,
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


  render() {
    let { packet_list } = this.state;

    return (
      <div>
        {this.state.submitted ?
          (
            <div>
              {packet_list.map((each) => (
                <div>
                  <Card title={`Frame ${each.indice}`} headStyle={{ backgroundColor: '#F5F5F5' }}>
                    <p>{each.captureSize} bits captured</p>
                    <p>Channel is {each.channel}</p>
                    <p>Source : {each.src} -{">"} Destination : {each.dst}</p>
                    <p>{each.protocol}</p>
                    <p>Mobile Country Code (MCC): {each.mcc}</p>
                    <p>Mobile Network Code (MNC): {each.mnc}</p>
                  </Card>
                  <br></br>
                </div>
              ))
              }
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