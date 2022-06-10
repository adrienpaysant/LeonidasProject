import { Popover, Typography } from 'antd';
import React, { Component } from 'react';


const { Title, Paragraph, Text } = Typography;

class About extends Component {

  state = {}

  setName(name) {
    return (
      <div>
        <p>contributors: {name}</p>
      </div>
    )
  }

  render() {
    return (
      <div className="container mt-3">
        <div >
          <Typography className="warp-content">
            <Title level={2}>About 4G Analyzer</Title>
            <Title level={3}>What is 4G analyzer</Title>
            <Paragraph>
              <Text>
                4G analyzer is a website that .....
                <a target='_blank' rel='noopener noreferrer' href='https://github.com/adrienpaysant/LeonidasProject#readme'>
                  README page
                </a>.
              </Text>
            </Paragraph>
            <Title level={3}>How does this work?</Title>
            <Paragraph>
              <Text>
                xxxxx<br />
              </Text>
            </Paragraph>
            <Title level={3}>Contributing</Title>
            <Paragraph>
              <Text>
                Open source: <a target='_blank' rel='noopener noreferrer' href="https://github.com/adrienpaysant/LeonidasProject" >https://github.com/adrienpaysant/LeonidasProject</a>
              </Text>
            </Paragraph>
            <Title level={3}>Developed by [RI50 team]</Title>
            <Paragraph>
              <Popover content={this.setName('Li Yuxiang')} >
                <a href="https://github.com/constantin942" target="_blank" rel="noopener noreferrer">
                  <span class="ant-avatar ant-avatar-circle ant-avatar-image">
                    <img src="https://avatars.githubusercontent.com/u/62027445?s=96&v=4" alt='yuxli' />
                  </span>
                </a>
              </Popover>
              <Popover content={this.setName('Adrien Paysant')} >
                <a href="https://github.com/adrienpaysant" target="_blank" rel="noopener noreferrer">
                  <span class="ant-avatar ant-avatar-circle ant-avatar-image">
                    <img src="https://avatars.githubusercontent.com/u/45820740?v=4" alt='adrienpaysant' />
                  </span>
                </a>
              </Popover>
            </Paragraph>
            <Title level={3}>Contacts & Support</Title>
            <Paragraph>
              <ul>
                <li>
                The website still has room for improvement, please send all your comments to <a href="mailto:yu.li@utbm.fr">yu.li@utbm.fr</a> for any purposes.
                </li>
                <li>
                  <a target='_blank' rel='noopener noreferrer' href="https://teams.microsoft.com/_#/conversations/19:fb90dd7044fa46f0804a4ec397480894@thread.v2?ctx=chat">
                    Help & contact on Microsoft Teams
                  </a>
                </li>
              </ul>
            </Paragraph>
            <Title level={3}>Version</Title>
            <Paragraph>
              <Text>
                1.0
              </Text>
            </Paragraph>
          </Typography>
          <br />
        </div>
      </div>
    )
  }
}

export default About
