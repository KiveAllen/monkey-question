declare namespace API {
  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseListInt_ = {
    code?: number;
    data?: number[];
    message?: string;
  };

  type BaseResponseListQuestionBankQuestionVO_ = {
    code?: number;
    data?: QuestionBankQuestionVO[];
    message?: string;
  };

  type BaseResponseLoginUserVO_ = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageQuestion_ = {
    code?: number;
    data?: PageQuestion_;
    message?: string;
  };

  type BaseResponsePageQuestionBank_ = {
    code?: number;
    data?: PageQuestionBank_;
    message?: string;
  };

  type BaseResponsePageQuestionBankVO_ = {
    code?: number;
    data?: PageQuestionBankVO_;
    message?: string;
  };

  type BaseResponsePageQuestionVO_ = {
    code?: number;
    data?: PageQuestionVO_;
    message?: string;
  };

  type BaseResponsePageUser_ = {
    code?: number;
    data?: PageUser_;
    message?: string;
  };

  type BaseResponsePageUserVO_ = {
    code?: number;
    data?: PageUserVO_;
    message?: string;
  };

  type BaseResponseQuestion_ = {
    code?: number;
    data?: Question;
    message?: string;
  };

  type BaseResponseQuestionBank_ = {
    code?: number;
    data?: QuestionBank;
    message?: string;
  };

  type BaseResponseQuestionBankVO_ = {
    code?: number;
    data?: QuestionBankVO;
    message?: string;
  };

  type BaseResponseQuestionVO_ = {
    code?: number;
    data?: QuestionVO;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BaseResponseUserVO_ = {
    code?: number;
    data?: UserVO;
    message?: string;
  };

  type DeleteRequest = {
    id?: number;
  };

  type doLoginUsingDELETEParams = {
    /** password */
    password?: string;
    /** username */
    username?: string;
  };

  type doLoginUsingGETParams = {
    /** password */
    password?: string;
    /** username */
    username?: string;
  };

  type doLoginUsingPATCHParams = {
    /** password */
    password?: string;
    /** username */
    username?: string;
  };

  type doLoginUsingPOSTParams = {
    /** password */
    password?: string;
    /** username */
    username?: string;
  };

  type doLoginUsingPUTParams = {
    /** password */
    password?: string;
    /** username */
    username?: string;
  };

  type getQuestionBankByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getQuestionBankQuestionUsingGETParams = {
    questionBankId?: number;
    questionId?: number;
  };

  type getQuestionByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getQuestionVOByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getUserByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserSignInRecordUsingGETParams = {
    /** year */
    year?: number;
  };

  type getUserVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type LoginUserVO = {
    createTime?: string;
    id?: number;
    updateTime?: string;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type ModelAndView = {
    empty?: boolean;
    model?: Record<string, any>;
    modelMap?: Record<string, any>;
    reference?: boolean;
    status?:
      | "CONTINUE"
      | "SWITCHING_PROTOCOLS"
      | "PROCESSING"
      | "CHECKPOINT"
      | "OK"
      | "CREATED"
      | "ACCEPTED"
      | "NON_AUTHORITATIVE_INFORMATION"
      | "NO_CONTENT"
      | "RESET_CONTENT"
      | "PARTIAL_CONTENT"
      | "MULTI_STATUS"
      | "ALREADY_REPORTED"
      | "IM_USED"
      | "MULTIPLE_CHOICES"
      | "MOVED_PERMANENTLY"
      | "FOUND"
      | "MOVED_TEMPORARILY"
      | "SEE_OTHER"
      | "NOT_MODIFIED"
      | "USE_PROXY"
      | "TEMPORARY_REDIRECT"
      | "PERMANENT_REDIRECT"
      | "BAD_REQUEST"
      | "UNAUTHORIZED"
      | "PAYMENT_REQUIRED"
      | "FORBIDDEN"
      | "NOT_FOUND"
      | "METHOD_NOT_ALLOWED"
      | "NOT_ACCEPTABLE"
      | "PROXY_AUTHENTICATION_REQUIRED"
      | "REQUEST_TIMEOUT"
      | "CONFLICT"
      | "GONE"
      | "LENGTH_REQUIRED"
      | "PRECONDITION_FAILED"
      | "PAYLOAD_TOO_LARGE"
      | "REQUEST_ENTITY_TOO_LARGE"
      | "URI_TOO_LONG"
      | "REQUEST_URI_TOO_LONG"
      | "UNSUPPORTED_MEDIA_TYPE"
      | "REQUESTED_RANGE_NOT_SATISFIABLE"
      | "EXPECTATION_FAILED"
      | "I_AM_A_TEAPOT"
      | "INSUFFICIENT_SPACE_ON_RESOURCE"
      | "METHOD_FAILURE"
      | "DESTINATION_LOCKED"
      | "UNPROCESSABLE_ENTITY"
      | "LOCKED"
      | "FAILED_DEPENDENCY"
      | "TOO_EARLY"
      | "UPGRADE_REQUIRED"
      | "PRECONDITION_REQUIRED"
      | "TOO_MANY_REQUESTS"
      | "REQUEST_HEADER_FIELDS_TOO_LARGE"
      | "UNAVAILABLE_FOR_LEGAL_REASONS"
      | "INTERNAL_SERVER_ERROR"
      | "NOT_IMPLEMENTED"
      | "BAD_GATEWAY"
      | "SERVICE_UNAVAILABLE"
      | "GATEWAY_TIMEOUT"
      | "HTTP_VERSION_NOT_SUPPORTED"
      | "VARIANT_ALSO_NEGOTIATES"
      | "INSUFFICIENT_STORAGE"
      | "LOOP_DETECTED"
      | "BANDWIDTH_LIMIT_EXCEEDED"
      | "NOT_EXTENDED"
      | "NETWORK_AUTHENTICATION_REQUIRED";
    view?: View;
    viewName?: string;
  };

  type OrderItem = {
    asc?: boolean;
    column?: string;
  };

  type PageQuestion_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: Question[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageQuestionBank_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: QuestionBank[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageQuestionBankVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: QuestionBankVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageQuestionVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: QuestionVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUser_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: User[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type Question = {
    answer?: string;
    content?: string;
    createTime?: string;
    editTime?: string;
    favourNum?: number;
    id?: number;
    isDelete?: number;
    needVip?: number;
    priority?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    source?: string;
    tags?: string;
    thumbNum?: number;
    title?: string;
    updateTime?: string;
    userId?: number;
    viewNum?: number;
  };

  type QuestionAddRequest = {
    answer?: string;
    content?: string;
    tags?: string[];
    title?: string;
  };

  type QuestionBank = {
    createTime?: string;
    description?: string;
    editTime?: string;
    id?: number;
    isDelete?: number;
    picture?: string;
    priority?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    title?: string;
    updateTime?: string;
    userId?: number;
    viewNum?: number;
  };

  type QuestionBankAddRequest = {
    description?: string;
    picture?: string;
    title?: string;
  };

  type QuestionBankQueryRequest = {
    current?: number;
    description?: string;
    id?: number;
    needQueryQuestionList?: boolean;
    notId?: number;
    pageSize?: number;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    tags?: string[];
    title?: string;
    userId?: number;
  };

  type QuestionBankQuestionAddRequest = {
    questionBankId?: number;
    questionId?: number;
  };

  type QuestionBankQuestionBatchAddRequest = {
    questionBankId?: number;
    questionIdList?: number[];
  };

  type QuestionBankQuestionBatchRemoveRequest = {
    questionBankId?: number;
    questionIdList?: number[];
  };

  type QuestionBankQuestionDeleteRequest = {
    questionBankId?: number;
    questionId?: number;
  };

  type QuestionBankQuestionVO = {
    id?: number;
    questionBankId?: number;
    questionId?: number;
  };

  type QuestionBankUpdateRequest = {
    description?: string;
    id?: number;
    picture?: string;
    title?: string;
  };

  type QuestionBankVO = {
    createTime?: string;
    description?: string;
    id?: number;
    picture?: string;
    questionPage?: PageQuestionVO_;
    title?: string;
    updateTime?: string;
    user?: UserVO;
    viewNum?: number;
  };

  type QuestionBatchDeleteRequest = {
    questionIdList?: number[];
  };

  type QuestionQueryRequest = {
    content?: string;
    current?: number;
    id?: number;
    notId?: number;
    pageSize?: number;
    questionBankId?: number;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    tags?: string[];
    title?: string;
    userId?: number;
  };

  type QuestionUpdateRequest = {
    answer?: string;
    content?: string;
    id?: number;
    tags?: string[];
    title?: string;
  };

  type QuestionVO = {
    answer?: string;
    content?: string;
    createTime?: string;
    id?: number;
    tagList?: string[];
    title?: string;
    updateTime?: string;
    user?: UserVO;
    userId?: number;
  };

  type User = {
    createTime?: string;
    editTime?: string;
    id?: number;
    inviteUser?: number;
    isDelete?: number;
    mpOpenId?: string;
    shareCode?: string;
    unionId?: string;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userPassword?: string;
    userProfile?: string;
    userRole?: string;
    vipCode?: string;
    vipExpireTime?: string;
    vipNumber?: number;
  };

  type UserAddRequest = {
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserQueryRequest = {
    current?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    userAccount?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserRegisterRequest = {
    checkPassword?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type UserUpdateMyRequest = {
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
  };

  type UserUpdateRequest = {
    id?: number;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserVO = {
    createTime?: string;
    id?: number;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type View = {
    contentType?: string;
  };
}
