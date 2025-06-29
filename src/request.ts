import axios from "axios"
import {message} from "ant-design-vue";

const myReq = axios.create({
  baseURL: "http://localhost:10086",
  timeout: 10000,
  withCredentials: true
})
myReq.interceptors.request.use(
  function (config) {
    return config
  },
  function (error) {
    return Promise.reject(error)
  },
)
myReq.interceptors.response.use(
  function (res) {
    const { data } = res
    // 未登录
    if (data.code === 40100) {
      // 不是获取用户信息的请求，并且用户目前不是已经在用户登录页面，则跳转到登录页面
      if (
        !res.request.responseURL.includes('user/me') &&
        !window.location.pathname.includes('/user/login')
      ) {
        window.location.href = `/user/login?redirect=${window.location.href}`
        message.warning('请先登录')
      }
    }
    return data
  },
  function (error) {
    return Promise.reject(error)
  },
)
export default myReq;
