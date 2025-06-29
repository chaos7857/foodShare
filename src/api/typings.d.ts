declare namespace API {
  type AddReviewRequest = {
    reviewMessage?: string
    reviewStatus?: number
    shareId?: number
  }

  type addShareUsingPOSTParams = {
    detail?: string
    tags?: string[]
    title?: string
  }

  type addStoreUpUsingPOSTParams = {
    /** shareId */
    shareId: number
  }

  type BaseResponseListReview_ = {
    code?: number
    data?: Review[]
    msg?: string
  }

  type BaseResponseLoginUserVO_ = {
    code?: number
    data?: LoginUserVO
    msg?: string
  }

  type BaseResponseLong_ = {
    code?: number
    data?: number
    msg?: string
  }

  type BaseResponsePageLoginUserVO_ = {
    code?: number
    data?: PageLoginUserVO_
    msg?: string
  }

  type BaseResponsePageShareVO_ = {
    code?: number
    data?: PageShareVO_
    msg?: string
  }

  type BaseResponsePageStoreup_ = {
    code?: number
    data?: PageStoreup_
    msg?: string
  }

  type BaseResponseString_ = {
    code?: number
    data?: string
    msg?: string
  }

  type DeleteRequest = {
    id?: number
  }

  type deleteStoreUpUsingPOSTParams = {
    /** id */
    id: number
  }

  type getUserUsingGETParams = {
    /** id */
    id: number
  }

  type listAllReviewUsingPOSTParams = {
    /** shareId */
    shareId: number
  }

  type LoginRequest = {
    password?: string
    userAccount?: string
  }

  type LoginUserVO = {
    createTime?: string
    id?: number
    updateTime?: string
    userAccount?: string
    userAvatar?: string
    userName?: string
    userProfile?: string
    userRole?: string
  }

  type PageLoginUserVO_ = {
    current?: number
    pages?: number
    records?: LoginUserVO[]
    size?: number
    total?: number
  }

  type PageRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
  }

  type PageShareVO_ = {
    current?: number
    pages?: number
    records?: ShareVO[]
    size?: number
    total?: number
  }

  type PageStoreup_ = {
    current?: number
    pages?: number
    records?: Storeup[]
    size?: number
    total?: number
  }

  type QueryByRSRequest = {
    current?: number
    pageSize?: number
    reviewStatus?: number
    sortField?: string
    sortOrder?: string
  }

  type RegisterRequest = {
    passwordConfirm?: string
    userAccount?: string
    userPassword?: string
  }

  type Review = {
    createTime?: string
    id?: number
    isDelete?: number
    reviewMessage?: string
    reviewStatus?: number
    updateTime?: string
    userId?: number
  }

  type ShareVO = {
    createTime?: string
    id?: number
    shareClickNum?: number
    shareDetail?: string
    shareLike?: number
    sharePicture?: string
    shareTags?: string[]
    shareTitle?: string
    updateTime?: string
    userId?: number
  }

  type Storeup = {
    createTime?: string
    group?: number
    id?: number
    isDelete?: number
    shareId?: number
    updateTime?: string
    userId?: number
  }

  type UpdateShareRequest = {
    id?: number
    shareDetail?: string
    shareTags?: string[]
    shareTitle?: string
  }

  type uploadFileUsingPOSTParams = {
    detail?: string
    tags?: string[]
    title?: string
  }

  type UserAddRequest = {
    userAccount?: string
    userName?: string
  }
}
