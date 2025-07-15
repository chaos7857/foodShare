// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** healthCheck GET /api/health */
export async function healthCheckUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseString_>('/api/health', {
    method: 'GET',
    ...(options || {}),
  })
}

/** adminHealthCheck GET /api/health/admin */
export async function adminHealthCheckUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseString_>('/api/health/admin', {
    method: 'GET',
    ...(options || {}),
  })
}

/** devHealthCheck GET /api/health/dev */
export async function devHealthCheckUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseString_>('/api/health/dev', {
    method: 'GET',
    ...(options || {}),
  })
}

/** userHealthCheck GET /api/health/user */
export async function userHealthCheckUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseString_>('/api/health/user', {
    method: 'GET',
    ...(options || {}),
  })
}
