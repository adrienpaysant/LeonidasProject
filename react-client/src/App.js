import "antd/dist/antd.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";
import { Link, Route, Switch } from "react-router-dom";
import "./App.css";
import TestEx from "./components/test.component";


class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            4G
          </Link>
          <div className="navbar-nav mr-auto">
            <li>
              <Link to={"/test"} className="nav-link">
                Test
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/"]} component={TestEx} />
            <Route exact path="/test" component={TestEx} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
