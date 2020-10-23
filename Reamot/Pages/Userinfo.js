import React from "react";
import { Button, Text, View, Alert, StyleSheet, TouchableOpacity } from "react-native";
import SpliteLine from '../Component/SpliteLine'

import Stroe from '../utils/localStroe'
export default class UserInfo extends React.Component {

    constructor() {
        super();
        this.state = {
            username: '',
            email: ''
        }
    }
    async _nameText() {

        Stroe.load({
            key: 'username',

            // autoSync(默认为true)意味着在没有找到数据或数据过期时自动调用相应的sync方法
            autoSync: true, // 设置为false的话，则等待sync方法提供的最新数据(当然会需要更多时间)。

            // syncInBackground(默认为true)意味着如果数据过期，
            // 在调用sync方法的同时先返回已经过期的数据。
            syncInBackground: true,
            // 你还可以给sync方法传递额外的参数
            syncParams: {
                extraFetchOptions: {
                    // 各种参数
                },
                someFlag: true,
            },
        })
            .then(ret => {
                // 如果找到数据，则在then方法中返回
                // 注意：这是异步返回的结果
                // 你只能在then这个方法内继续处理ret数据
                // 而不能在then以外处理
                // 也没有办法“变成”同步返回
                // 你也可以使用“看似”同步的async/await语法
                console.log(ret.name);
                this.setState({ username: ret.name });
            })
            .catch(err => {
                //如果没有找到数据且没有sync方法，
                //或者有其他异常，则在catch中返回
                console.warn(err.message);
                switch (err.name) {
                    case 'NotFoundError':
                        // TODO;
                        break;
                    case 'ExpiredError':
                        // TODO
                        break;
                }
            });

    }
    _emailText() {
    }


    _Data_Access() {

    }
    _Setting() {

    }
    _Prescription(){
        this.props.navigation.navigate('Prescription')

    }

    _LogOut() {
        this.props.navigation.navigate('Home')
    }
    render() {
        return (
            <View style={styles.mainview}>
                <View style={styles.userprofile}>
                    <Text style={styles.name} ref="name" onPress={() => this._nameText()}>
                        {this.state.username}
                    </Text>
                    <Text style={styles.email} onPress={() => this._emailText()} >
                        {this.state.email}
                    </Text>
                </View>
                <View style={styles.buttonlistview}>
                    <TouchableOpacity style={styles.databtn} onPress={() => this._Data_Access()}>
                        <Text style={styles.databtntext} >
                            Data Access
                        </Text>
                    </TouchableOpacity>
                    <SpliteLine></SpliteLine>
                    <TouchableOpacity style={styles.settingbtn} onPress={() => this._Setting()}>
                        <Text style={styles.settingbtntext}>
                            Setting
                        </Text>
                    </TouchableOpacity>
                    <SpliteLine></SpliteLine>
                    <TouchableOpacity style={styles.logoutbtn} onPress={() => this._LogOut()}>
                        <Text style={styles.logoutbtntext}>
                            Logout
                        </Text>
                    </TouchableOpacity>
                    <SpliteLine></SpliteLine>
                    <TouchableOpacity style={styles.next} onPress={() => this._Prescription()}>
                        <Text style={styles.btntext}>
                        Prescription
                        </Text>
                    </TouchableOpacity>
                </View>
            </View>
        );
    }
}
const styles = StyleSheet.create({
    mainview: {
        justifyContent: 'flex-start',
        flex: 1
    },
    userprofile: {
        height: 300,
        width: '100%',
        backgroundColor: 'rgb(104, 175, 93)',
        padding: 20
    },
    name: {
        marginTop: 200,
        marginBottom: 10,
        color: 'white',
        fontSize: 30
    },
    email: {
        fontSize: 15,
        color: 'white'
    },
    buttonlistview: {
        padding: 20
    },
    databtn: {
        margin: 10
    },
    databtntext: {
        fontSize: 25

    },
    settingbtn: {
        margin: 10
    },
    settingbtntext: {
        fontSize: 25

    },
    logoutbtn: {
        margin: 10
    },
    logoutbtntext: {
        fontSize: 25

    }
})


