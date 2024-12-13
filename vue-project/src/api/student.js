import request from '@/utils/request.js'


//提供调用登录接口的函数
export const studentLoginService = (loginData)=>{
    const params = new URLSearchParams();
    for(let key in loginData){
        params.append(key,loginData[key])
    }
    return request.post('/student/login',params)
}
//获取用户个人画像
export const getportrait =()=>{
    return request.get('/student/portrait')
}
export const getradar =()=>{
    return request.get('/student/radar')
}
export const getwarn =()=>{
    return request.get('/student/warn')
}
export const getportrait_life =()=>{
    return request.get('/student/portrait_life')
}
export const getradar_life =()=>{
    return request.get('/student/radar_life')
}
export const getwarn_life =()=>{
    return request.get('/student/warn_life')
}
export const studentInfo =()=>{
    return request.get('/student/studentinfo')
}