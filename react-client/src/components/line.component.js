import { Line } from '@ant-design/plots';
import React, { useEffect, useState } from 'react';

export const DemoLine = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    asyncFetch();
  }, []);

  const asyncFetch = () => {
    fetch('https://gw.alipayobjects.com/os/bmw-prod/1d565782-dde4-4bb6-8946-ea6a38ccf184.json')
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => {
        console.log('fetch data failed', error);
      });
  };
  const config = {
    data,
    padding: 'auto',
    xField: 'Date',
    yField: 'scales',
    xAxis: {
      // type: 'timeCat',
      tickCount: 5,
    },
    tooltip: {
      customContent: (title, items) => { 
        return (
          <>
          <div style={
            {
              width: 106,
              // height: 132,
              padding: 10,
              backgroundColor: 'rgba(0, 0, 0, 0.75)',
              color: 'white',
              fontSize: 12,
              margin: '0 -12px 0 -12px',
              borderRadius: 2,
              }}>
            <h5 style={{ marginTop: 0, color: 'white'}}>{title}</h5>
            <ul style={{ paddingLeft: 0 }}>
              {items?.map((item, index) => {
                const { name, value, color } = item;
                return (
                  <li
                    key={item.year}
                    className="g2-tooltip-list-item"
                    data-index={index}
                    style={{ marginBottom: 4, display: 'flex', alignItems: 'center' }}
                  >
                    <span className="g2-tooltip-marker" style={{ backgroundColor: color }}></span>
                    <span
                      style={{ display: 'inline-flex', flex: 1, justifyContent: 'space-between' }}
                    >
                      <span style={{ margiRight: 16 }}>{name}:</span>
                      <span className="g2-tooltip-list-item-value">{value}</span>
                    </span>
                  </li>
                );
              })}
            </ul>
            </div>
          </>
        );
      },
    },

  };

  return <Line {...config} />;
};

// ReactDOM.render(<DemoLine />, document.getElementById('container'));
