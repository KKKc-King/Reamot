import WelcomeScreen from '../Pages/WelcomeScreen'
import Register from '../Pages/Register'
import Userinfo from '../Pages/Userinfo'
import ForGetPw from '../Pages/ForGetPw'
import Prescription from '../Pages/Prescription'
import { createAppContainer } from 'react-navigation'
import { createStackNavigator } from 'react-navigation-stack'

const RouterConfig = {
    Home: {
        screen: WelcomeScreen,
        navigationOptions: {
            headerShown: false,
            headerMode: 'screen'
        }
    },
    Register: {
        screen: Register,
        navigationOptions: {
            headerBackTitleVisible: false,
            headerMode: 'screen'
        }
    },
    Userinfo: {
        screen: Userinfo,
        navigationOptions: {
            headerBackTitleVisible: false,
            headerMode: 'screen'
        }
    },
    ForGetPw: {
        title: 'Forget Password',
        screen: ForGetPw,
        navigationOptions: {
            headerBackTitleVisible: false,
            headerMode: 'screen'
        }
    },
    Prescription: {
        title: 'Prescription',
        screen: Prescription,
        navigationOptions: {
            headerBackTitleVisible: false,
            headerMode: 'screen'
        }
    }
}

const Navigators = createStackNavigator(RouterConfig);
const Appcontianer = createAppContainer(Navigators)
export default Appcontianer;