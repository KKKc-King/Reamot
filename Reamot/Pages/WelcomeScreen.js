import React from "react";
import {
  ImageBackground,
  StyleSheet,
  Text,
  TextInput,
  Image,
  View, TouchableOpacity, Alert
} from "react-native";

import Stroe from '../utils/localStroe'

import httpApi from '../utils/api'
export default class WelcomeScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
    }
  }
  _UsernameText(inputdata) {
    this.setState({ username: inputdata });
  }
  _PasswordText(inputdata) {
    this.setState({ password: inputdata });

  }
  async _Login() {

    let params = {
      username: this.state.username,
      password: this.state.password,
    };
    // console.log(params);

    //2.调用请求接口 ,传入参数即可
    let res = await httpApi.login(params);
    // console.log(res)
    if (res.success) {
      Alert.alert("Login Success")
      Stroe.save({
        key: 'username', // Note: Do not use underscore("_") in key!
        data: {
          name: this.state.username
        },

        // if expires not specified, the defaultExpires will be applied instead.
        // if set to null, then it will never expire.
        expires: 1000 * 3600
      });
      // var keyValuePairs = [['name', this.state.username]]
      // AsyncStorage.multiSet(keyValuePairs, function (errs) {
      //   if (errs) {
      //     console.log(errs)
      //     return;
      //   }
      //   alert('数据保存成功!');
      // });
      this.props.navigation.navigate('Userinfo');
    } else {
      Alert.alert("用户名或密码错误")
      return;
    }
    // todo 成功跳转，失败弹出提示
    // this.props.navigation.navigate('Userinfo')
  }
  _ForgetPasswd() {
    this.props.navigation.navigate('ForGetPw')
  }
  _Register() {
    this.props.navigation.navigate('Register')
  }

  render() {
    return (
      <ImageBackground source={require("../assets/Background.jpg")} style={styles.ImageBackgrounds}>
        <Image source={require("../assets/favicon.png")} style={{ top: 140, position: "absolute", alignItems: 'center' }}>
        </Image>
        <View style={styles.appname}>
          <Text>Reamot</Text>
        </View>
        <View style={styles.inputsettingusername}>
          <TextInput style={styles.username} onChangeText={(username) => this._UsernameText(username)} placeholder=" Username" placeholderTextColor="white" textAlign="left" selectionColor="cyan" allowFontScaling={true}>
          </TextInput>
        </View>
        <View style={styles.inputsettingpassword}>
          <TextInput style={styles.password} onChangeText={(password) => this._PasswordText(password)} placeholder=" Password" placeholderTextColor="white" secureTextEntry={true} textAlign="left" selectionColor="cyan" allowFontScaling={true}>
          </TextInput>
        </View>
        <View style={styles.LoginButtonView}>
          <TouchableOpacity style={styles.LoginButton} onPress={() => this._Login()} >
            <Text style={styles.LoginButtonText}>
              Login
            </Text>
          </TouchableOpacity>
        </View>
        <View style={styles.topviewstyle}>
          <TouchableOpacity style={styles.forgetbtnstyle} onPress={() => this._ForgetPasswd()}>
            <Text style={{ color: '#fff' }}>
              Forget Password
            </Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.registerbtnstyle} onPress={() => this._Register()}>
            <Text style={{ color: '#fff' }}>
              Register
            </Text>
          </TouchableOpacity>
        </View>
      </ImageBackground >
    );
  }
}

const styles = StyleSheet.create({
  ImageBackgrounds: {
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'center'
  },
  appname: {
    position: "absolute",
    color: 'cyan',
    top: 190,
    alignItems: 'center',
  },
  inputsettingusername: {
    position: 'absolute',
    alignItems: 'center',
    justifyContent: 'center',
    top: 400,
  },
  inputsettingpassword: {
    position: 'absolute',
    top: 470,
    alignItems: 'center',
    justifyContent: 'center',
  },
  username: {
    width: 300,
    height: 50,
    color: 'cyan',
    justifyContent: 'center',
    alignItems: 'center',
    borderColor: 'gray',
    borderWidth: 1,
    borderRadius: 30,
    padding: 10,
  },
  password: {
    width: 300,
    height: 50,
    alignItems: 'center',
    borderColor: 'gray',
    color: 'cyan',
    borderWidth: 1,
    borderRadius: 30,
    padding: 10
  },
  LoginButtonView: {
    position: 'absolute',
    alignItems: 'center',
    justifyContent: 'center',
    top: 550
  },
  LoginButton: {
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'black',
    borderRadius: 30,
    width: 300,
    height: 40
  },
  LoginButtonText: {
    color: 'white'
  },
  topviewstyle: {
    flexDirection: 'row',
    position: 'absolute',
    justifyContent: 'space-around',
    top: 600
  },
  forgetbtnstyle: {
    width: 150,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',

  },
  registerbtnstyle: {
    width: 150,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
  }
});