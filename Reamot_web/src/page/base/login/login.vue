<template>
  <div class="wrap" id="app2">
    <div class="loginBox">
      <h3>Reamot Manager</h3>
      <el-form
        ref="loginForm"
        :model="loginForm"
        class="login-form"
        :rules="rules"
        label-width="80px"
        label-position="left"
      >
        <el-form-item label="usename" prop="name">
          <el-input v-model="loginForm.name"></el-input>
        </el-form-item>
        <el-form-item label="password" prop="password">
          <el-input v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="handleLogin">Login</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import Storge from "../../../store/store";
export default {
  name: "Login",
  data() {
    return {
      loginForm: {},
      rules: {
        name: [
          {
            required: true,
            message: "please enter user name",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "please enter your password",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.openTip();
  },
  methods: {
    handleLogin() {
      let url =
        "http://localhost:9999/doctor/login?username=" +
        this.loginForm.name +
        "&password=" +
        this.loginForm.password;
      console.log(url);
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          this.$http
            .get(url, {})
            .then(({ body }) => {
              if (body.success === true) {
                localStorage.username = this.loginForm.name;
                window.location.href = "/doctor";
              } else {
                this.$message.error(body.message);
              }
            })
            .catch((err) => {
              console.log(err);
              this.$message.error("login failed");
            });
        }
      });
    },
    openTip() {
      this.$notify({
        title: "tips",
        message: "Weclome Admin！",
      });
    },
  },
};
</script>
<style src="../../../style/common.scss"></style>
<style scoped lang="scss">
.wrap {
  width: 100%;
  height: 100%;
  background: url("../../../assets/img/bg2.jpeg") no-repeat;
  background-size: 100% 100%;

  .loginBox {
    width: 355px;
    height: 232px;
    padding: 40px;
    position: absolute;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    background: white;
    text-align: center;
    box-shadow: 1px 2px 1px rgba(0, 0, 0, 0.15);
    padding-top: 31px;

    .el-form {
      margin-top: 30px;

      .el-button {
        width: 100%;
      }
    }
  }
}
</style>
