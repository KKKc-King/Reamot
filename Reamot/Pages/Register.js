import React from "react";
import { TextInput, View, Alert, TouchableOpacity, StyleSheet, Text } from "react-native";
import httpApi from '../utils/api'

export default class Register extends React.Component {

    constructor() {
        super();
        this.state = {
            uid: null,
            username: "",
            password: "",
            email: "",
            phone: "",
            mnumber: "",
            repassword: ""
        }
    }

    _UserText(inputdata) {
        // console.log(inputdata);
        this.setState({ username: inputdata });
    }
    _EmailText(inputdata) {
        // console.log(inputdata);
        this.setState({ email: inputdata });

    }
    _PasswordText(inputdata) {
        this.setState({ password: inputdata });
    }
    _RepasswdText(inputdata) {
        this.setState({ repassword: inputdata });

    }
    _PhoneText(inputdata) {
        this.setState({ phone: inputdata })
    }
    _MnumberText(inputdata) {
        this.setState({ mnumber: inputdata })
    }
    async _Register() {

        let params = {
            uid: null,
            username: this.state.username,
            password: this.state.password,
            phone: this.state.phone,
            email: this.state.email,
            mnumber: this.state.mnumber
        };
        console.log(params);

        // 2.调用请求接口 ,传入参数即可
        let res = await httpApi.register(params);
        console.log(res)
        if (res.success) {
            Alert.alert("您已经注册成功了");
            // Store.storeData("username", res.data.username);
            this.props.navigation.navigate('Userinfo');
        } else {
            Alert.alert("用户名已经存在，请重新输入")
            return;
        }
        // todo 成功跳转，失败弹出提示

    }

    render() {
        return (
            <View style={styles.mainView}>
                <View style={styles.userinfolist}>
                    <View style={styles.nameview}>
                        <TextInput style={styles.nameinput} placeholder='Name' onChangeText={(name) => this._UserText(name)}>
                        </TextInput>
                    </View>
                    <View style={styles.emailview}>
                        <TextInput style={styles.emailinput} placeholder='Email' keyboardType='email-address' onChangeText={(email) => this._EmailText(email)}>
                        </TextInput>
                    </View>
                    <View style={styles.passwdview}>
                        <TextInput style={styles.passwdinput} placeholder='Password' secureTextEntry={true} clearButtonMode='while-editing' onChangeText={(password) => this._PasswordText(password)}>
                        </TextInput>
                    </View>
                    <View style={styles.repasswdview}>
                        <TextInput style={styles.repassinput} placeholder='Confirm Password' secureTextEntry={true} clearButtonMode='while-editing' onChangeText={(repassword) => this._RepasswdText(repassword)}>
                        </TextInput>
                    </View>
                    <View style={styles.phoneview}>
                        <TextInput style={styles.phoneinput} placeholder='Phone' keyboardType='number-pad' onChangeText={(phone) => this._PhoneText(phone)}>
                        </TextInput>
                    </View>
                    <View style={styles.mnumberview}>
                        <TextInput style={styles.mnumberinput} placeholder='Medicare Number' keyboardType='number-pad' onChangeText={(mnumber) => this._MnumberText(mnumber)}>
                        </TextInput>
                    </View>
                </View>
                <View style={styles.registerbtnview}>
                    <TouchableOpacity style={styles.registerbtnstyle}
                        onPress={() => this._Register()}>
                        <Text style={styles.registerbtntext}>
                            Register
                            </Text>
                    </TouchableOpacity>
                </View>
            </View>

        );
    }
}
const styles = StyleSheet.create({
    mainView: {
        flex: 1,
        justifyContent: 'flex-start',
        alignItems: 'center'
    },
    userinfolist: {
        justifyContent: 'center',
        alignItems: 'center',
        top: 150,
    },
    nameview: {
        margin: 15
    },
    nameinput: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    emailview: {
        margin: 15
    },
    emailinput: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    passwdinput: {
        margin: 15
    },
    passwdinput: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    repasswdview: {
        margin: 15
    },
    repassinput: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    phoneinput: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    phoneview: {
        margin: 15
    },
    mnumberinput: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    mnumberview: {
        margin: 15
    },
    registerbtnview: {
        position: 'absolute',
        alignItems: 'center',
        justifyContent: 'center',
        top: 550,

    },
    registerbtnstyle: {
        height: 40,
        width: 300,
        borderColor: 'gray',
        borderWidth: 1,
        borderRadius: 20,
        backgroundColor: 'black',
        alignItems: 'center',
        justifyContent: 'center'
    },
    registerbtntext: {
        color: 'white'
    }

})