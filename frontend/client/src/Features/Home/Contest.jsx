import React, { useEffect, useState } from 'react'
import Api from '../../Api'

const Contest = () => {

  const [contest, setContest] = useState()

  useEffect(()=> {
    Api.get("/Problems-Contest-Service/api/v1/get-all-contest")
    .then((res)=> {
      console.log(res.data)
    }).catch((err)=> {})
  }, [])

  return (
    <div>
      <h1> 
        Contest 
      </h1>
    </div>
  )
}

export default Contest