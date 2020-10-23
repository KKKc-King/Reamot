import Register from '../Pages/Register';
import http from './https';
// promise的封装操作
function to(promise) {
    return promise
        .then(data => {
            return [null, data];
        })
        .catch(err => [err]);
}

const httpApi = {
    // login请求
    async login(params) {
        const url = '/user/login';
        const [err, res] = await to(http.get(url, params));
        if (err) {
            //请求失败
            return Object.assign(err, {
                status: "406",
                description: err.errMsg
            }, true);
        }
        //请求成功
        return res;
    },
    //logout 请求
    async logout(params) {
        const url = '/user';
        const [err, res] = await to(http.get(url, params));
        if (err) {
            return Object.assign(err, {
                status: "406",
                description: err.errMsg
            }, true);
        }
        return res;
    },
    async register(params) {
        const url = '/user';
        const [err, res] = await to(http.post(url, params));
        if (err) {
            return Object.assign(err, {
                status: "406",
                description: err.errMsg
            }, true);
        }
        return res;
    }
    // TODO  其他业务请求.....
}
export default httpApi;
