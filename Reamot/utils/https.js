let common_url = 'http://localhost:9999'; //请求的url
let header = {
    // "Content-Type": "application/x-www-form-urlencoded",
    "Content-Type": "application/json",
    "Cache-Control": "no-cache",
    // "accesstoken": token  //用户登陆后返回的token，某些涉及用户数据的接口需要在header中加上token
};


const http = {
    get(url, params) {
        if (params) {
            let paramsArray = [];
            //拼接参数
            Object.keys(params).forEach(key => paramsArray.push(key + '=' + params[key]))
            if (url.search(/\?/) === -1) {
                url += '?' + paramsArray.join('&')
            } else {
                url += '&' + paramsArray.join('&')
            }
        }

        return new Promise(function (resolve, reject) {
            fetch(common_url + url)
                .then((response) => response.json()
                    //将返回数据转化为json，也可以转为text(),否则无法看到返回数据,且必须回调才可以看到
                )
                .then((responseData) => {
                    resolve(responseData);
                })
                .catch((err) => {
                    console.log("err:", err)
                    reject(err);
                });
        });
    },
    post(url, params) {
        return new Promise(function (resolve, reject) {

            fetch(common_url + url, {
                method: 'POST',
                headers: header,
                body: JSON.stringify(params)   //body参数，通常需要转换成字符串后服务器才能解析
            }).then((response) => response.json())
                .then((responseData) => {
                    console.log('res:', url, responseData);   //网络请求成功返回的数据
                    resolve(responseData);
                })
                .catch((err) => {
                    console.log('err:', url, err);   //网络请求失败返回的数据  
                    reject(err);
                });
        });
    },
};
//导出
export default http;
