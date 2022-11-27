import React from 'react'
import Home from "../Features/Home"
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useRouteMatch,
  useParams,
}
  from "react-router-dom"
import Navbar from "../Components/Navbar/index"
import Contest from "../Features/Home/Contest"
const Routes = () => {
  return (
    <Switch>
      <Route exact path="/contest">
        <Navbar />
        <Contest />
      </Route>
      <Route exact path="/home">
        <Home />
      </Route>
    </Switch >
  )
}

export default Routes 