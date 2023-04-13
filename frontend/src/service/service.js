import Axios from 'axios'

export const Service = Axios.create({
    baseURL: process.env.VUE_APP_BACKEND_BASE_URL + "/",
    timeout:10000,
    headers:{
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET",
        "Access-Control-Allow-Headers": "*"
    }
})
Service.interceptors.response.use(response => response)