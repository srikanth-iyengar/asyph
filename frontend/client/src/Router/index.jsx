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

const Routes = () => {
  return (
    <Switch>
      <Route path="/contest">
        <h3>Contest List</h3>
      </Route>
      <Route path="/">
        <Home />
      </Route>
    </Switch >
  )
}

export default Routes 