import React from 'react'
import axios from "axios"

export default axios.create({
    baseURL: "https://gateway-srikanth-iyengar.cloud.okteto.net/api/v1"
})