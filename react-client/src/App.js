import "antd/dist/antd.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";
import { Link, Route, Switch } from "react-router-dom";
import "./App.css";
import TestEx from "./components/pcap.component";
import { DemoLine } from "./components/line.component";
import About from "./components/aboutpage.component";


class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            4G Analyzer
          </Link>
          <div className="navbar-nav mr-auto">
            <li>
              <Link to={"/about"} className="nav-link">
                About
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/"]} component={TestEx} />
            <Route exact path="/about" component={About} />
            <Route exact path="/test" render={() => <div><DemoLine /></div>} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
