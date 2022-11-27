import React, { useEffect, useState } from 'react'
import Api from '../../Api'
import "./contest.css"
import { Chip } from '@mui/material'

const Contest = () => {

  const [contest, setContest] = useState([])

  useEffect(() => {
    Api.get("/Problems-Contest-Service/get-all-contest")
      .then((res) => {
        setContest(res.data)
      }).catch((err) => { })
  }, [])

  const getColor = (status) => {
    if(status === "NOT_STARTED") {
      return "primary"
    }
    else if(status == "RUNNING") {
      return "success"
    }
    else {
      return "error";
    }
  }

  return (
    <div className='contest-container'>
      {contest.map((cont) => (
        <div class="img-card iCard-style1">
          <div class="card-content">
            <div class="card-image">
              <span class="card-title">{cont.contestName}</span>
              <img src="https://www.dropbox.com/s/u330jm6faybxrvb/fog-3461451_640.jpg?raw=1" />
            </div>

            <div class="card-text">
              <p>
                Start Time: {cont.startTime}
              </p>
              <p>
                End Time: {cont.endTime}
              </p>
              <p>
                Created By: {cont.createdBy}
              </p>
            </div>
          </div>
          <div class="card-link" style={{display: "flex", flexDirection: "row"}}>
            <p>
              <Chip color={getColor(cont.status)} label={cont.status} />
            </p>
            <a href={"contest/"+cont.contestId} title="Read Full"><span>Know More</span></a>
          </div>
        </div>
      ))}
    </div>
  )
}

export default Contest