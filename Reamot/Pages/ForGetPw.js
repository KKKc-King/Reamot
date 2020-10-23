import React from "react";
import { TextInput, View, Alert, StyleSheet, TouchableOpacity, Text } from "react-native";

export default class FooGetPw extends React.Component {

    constructor() {
        super();
    }

    _Register() {
        let param = {
            email: ""
        }
        this.props.navigation.navigate('Register')
    }
    render() {
        return (
            <View style={{
                flex: 1, justifyContent: 'flex-end',
                alignItems: 'center'
            }}>
                <View style={styles.emailview}>
                    <TextInput style={styles.email} placeholder="Email" keyboardType='email-address'>
                    </TextInput>
                </View>
                <View style={styles.registerbtnview}>
                    <TouchableOpacity style={styles.registerbtnstyle} onPress={() => this._Register()}>
                        <Text style={styles.registerbtntext}>
                            Register
            </Text>
                    </TouchableOpacity>
                </View >
            </View>
        );
    }
}
const styles = StyleSheet.create({
    emailview: {
        position: 'absolute',
        alignItems: 'center',
        justifyContent: 'center',
        top: 300,

    },
    email: {
        width: 300,
        height: 40,
        justifyContent: 'center',
        borderRadius: 20,
        borderColor: 'gray',
        borderWidth: 1,
        padding: 10
    },
    registerbtnview: {
        position: 'absolute',
        alignItems: 'center',
        justifyContent: 'center',
        top: 350,

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