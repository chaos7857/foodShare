// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** addReview POST /api/review/add */
export async function addReviewUsingPost(
  body: API.AddReviewRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseLong_>('/api/review/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** listAllReview POST /api/review/page/all */
export async function listAllReviewUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listAllReviewUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseListReview_>('/api/review/page/all', {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}
