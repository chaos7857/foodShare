// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** addStoreUp POST /api/storeup/add */
export async function addStoreUpUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addStoreUpUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseLong_>('/api/storeup/add', {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** deleteStoreUp POST /api/storeup/delete */
export async function deleteStoreUpUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteStoreUpUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseString_>('/api/storeup/delete', {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** getMyStoreUp POST /api/storeup/my */
export async function getMyStoreUpUsingPost(
  body: API.PageRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageStoreup_>('/api/storeup/my', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}
