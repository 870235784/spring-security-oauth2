1.使用授权码方式
    step1: 获取授权码code
        get  http://localhost:8090/oauth/authorize?client_id=app_tca&response_type=code
    step2: 根据授权码code获取token
        post http://localhost:8090/oauth/token
        
2.使用简化模式方式
    浏览器get  http://localhost:8090/oauth/authorize?client_id=app_tca&response_type=token
    