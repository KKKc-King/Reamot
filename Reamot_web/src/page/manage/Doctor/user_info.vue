<template>
  <div class="main-login">
    <lyz-layout
      :pagination="pagination"
      :label="label"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange"
    >
      <div slot="banner" class="top-right">
        <el-button
          type="primary"
          size="small"
          @click="createUser"
          slot="reference"
        >
          Add</el-button
        >
        &nbsp;
      </div>
      <div slot="main" class="main-body">
        <el-table
          :data="tableData"
          stripe
          v-loading="loginLoading"
          tooltip-effect="light"
          height="100%"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <!--          <el-table-column type="selection" width="55"></el-table-column>-->
          <el-table-column
            v-for="(data, index) in tableHeader"
            :show-overflow-tooltip="true"
            :key="index"
            :prop="data.prop"
            :label="data.label"
            :min-width="data['min-width']"
            :align="data.align"
          >
          </el-table-column>
          <el-table-column
            fixed="right"
            label="operating"
            align="center"
            min-width="120"
          >
            <template slot-scope="scope">
              <el-button
                type="text"
                size="mini"
                class="el-button--info"
                @click="modifyUser(scope.row.uid)"
                >Modify
              </el-button>
              <el-button
                type="text"
                size="mini"
                class="danger-text"
                @click="deleteUser(scope.row.uid)"
                >Delete
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </lyz-layout>
    <el-dialog
      :title="'Add patient information'"
      :visible.sync="messageVisible"
      width="33%"
      center
      class="user-dialog"
    >
      <el-form
        :model="messageForm"
        :label-width="messageLabelWidth"
        ref="messageForm"
        :rules="messageRule"
        :validate-on-rule-change="false"
      >
        <el-form-item label="username" prop="username">
          <el-input
            v-model="messageForm.username"
            placeholder="Please enter user name"
          ></el-input>
        </el-form-item>

        <el-form-item label="password" prop="password">
          <el-input
            v-model="messageForm.password"
            placeholder="Please enter password"
          ></el-input>
        </el-form-item>

        <el-form-item label="Phone" prop="phone">
          <el-input
            v-model="messageForm.phone"
            placeholder="Please enter phone number"
          ></el-input>
        </el-form-item>

        <el-form-item label="email" prop="email">
          <el-input
            v-model="messageForm.email"
            placeholder="Please enter email"
          ></el-input>
        </el-form-item>
        <el-form-item label="Medcode" prop="mnumber">
          <el-input
            v-model="messageForm.mnumber"
            placeholder="Please enter Medical insurance number"
          ></el-input>
        </el-form-item>
        <el-form-item label="Medicine" prop="medicine">
          <el-input
            v-model="messageForm.medicine"
            placeholder="Please enter Medicine"
          ></el-input>
        </el-form-item>
        <el-form-item label="Status" prop="status">
          <el-input
            v-model="messageForm.status"
            placeholder="Please enter Status"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="messageVisible = false">Cancel</el-button>

        <el-button type="primary" @click="updateUser" v-if="isModify"
          >Update</el-button
        >
        <el-button type="primary" @click="saveUser" v-else>Add</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
function dateFormat(fmt, date) {
  let ret;
  const opt = {
    "Y+": date.getFullYear().toString(), // 年
    "m+": (date.getMonth() + 1).toString(), // 月
    "d+": date.getDate().toString(), // 日
    "H+": date.getHours().toString(), // 时
    "M+": date.getMinutes().toString(), // 分
    "S+": date.getSeconds().toString(), // 秒
    // 有其他格式化字符需求可以继续添加，必须转化成字符串
  };
  for (let k in opt) {
    ret = new RegExp("(" + k + ")").exec(fmt);
    if (ret) {
      fmt = fmt.replace(
        ret[1],
        ret[1].length === 1 ? opt[k] : opt[k].padStart(ret[1].length, "0")
      );
    }
  }
  return fmt;
}

function convertData(ms) {
  let date = new Date(ms);
  return dateFormat("YYYY-mm-dd HH:MM", date);
}

import lyzLayout from "@/components/lyzLayout";
import manage from "../manage.component";
import { responseText } from "../../../config/utils.js";

export default {
  name: "user",
  data() {
    return {
      queryType: "",
      queryKeyword: "",
      pagination: {
        pageIndex: 1,
        pageSize: 10,
        total: 0,
      },
      label: "Patient information management",
      messageForm: {},
      messageVisible: false,
      messageLabelWidth: "90px",
      isModify: false,
      messageRule: {
        username: [
          { required: true, message: "please enter username", trigger: "blur" },
        ],
        password: [
          { required: true, message: "please enter password", trigger: "blur" },
        ],
        phone: [
          {
            required: true,
            message: "please enter phone number",
            trigger: "blur",
          },
        ],
        email: [
          { required: true, message: "please enter email", trigger: "blur" },
        ],
        mnumber: [
          {
            required: true,
            message: "please enter Medicine Code",
            trigger: "blur",
          },
        ],
      },
      multipleSelection: [], //多选的数据
      pickerOptions: {},
      tableData: [],
      loginLoading: false,
      tableHeader: [
        {
          prop: "uid",
          label: "ID",
          "min-width": 40,
          align: "center",
        },
        {
          prop: "username",
          label: "username",
          "min-width": 60,
          align: "center",
        },
        {
          prop: "password",
          label: "password",
          "min-width": 60,
          align: "center",
        },
        {
          prop: "phone",
          label: "phone",
          "min-width": 60,
          align: "center",
        },
        {
          prop: "email",
          label: "phone",
          "min-width": 120,
          align: "center",
        },
        {
          prop: "mnumber",
          label: "mnumber",
          "min-width": 60,
          align: "center",
        },
        {
          prop: "medicine",
          label: "medicine",
          "min-width": 60,
          align: "center",
        },
        {
          prop: "status",
          label: "status",
          "min-width": 60,
          align: "center",
        },
      ],
    };
  },
  components: {
    lyzLayout,
  },
  mixins: [manage],
  created() {
    this.queryList();
  },
  mounted() {
    // this.$watch('queryKeyword', debounce(() => {
    //   this.pagination.pageIndex = 1;
    //   this.queryList();
    // }, 1000));
  },
  methods: {
    queryList() {
      this.loginLoading = true;
      let params = {
        page: this.pagination.pageIndex,
        pageCount: this.pagination.pageSize,
      };
      this.$http
        .get("http://localhost:9999/user", { params: params })
        .then(({ body }) => {
          if (body.success === true) {
            responseText(body.data.records);
            this.tableData = responseText(body.data.records);
            this.pagination.total = body.data.records ? body.data.total : 0;
          } else {
            this.$message.error(body.message);
          }
        })
        .finally(() => {
          this.loginLoading = false;
        });
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteUser(id) {
      // console.log("delete"+id)
      this.delete("/user/" + id);
    },
    modifyUser(id) {
      console.log("mofify");
      this.isModify = true;
      this.$http
        .get("http://localhost:9999/user/" + id)
        .then(({ body }) => {
          if (body.success === true) {
            let params = {
              id: id,
              username: body.data.username,
              password: body.data.password,
              phone: body.data.phone,
              email: body.data.email,
              mnumber: body.data.mnumber,
              medicine: body.data.medicine,
              status: body.data.status,
            };

            this.messageVisible = true;
            let _form = Object.assign({}, id);

            _form.id = params.id;
            _form.username = params.username;
            _form.password = params.password;
            _form.phone = params.phone;
            _form.email = params.email;
            _form.mnumber = params.mnumber;
            _form.medicine = params.medicine;
            _form.status = params.status;

            this.messageForm = _form;
          } else {
            this.$message.error(body.message);
          }
        })
        .finally(() => {
          this.loginLoading = false;
        });
    },
    createUser(row) {
      console.log("create");
      this.isModify = false;
      console.log(row);
      this.messageVisible = true;
      let _form = Object.assign({}, row);
      this.messageForm = _form;
    },
    saveUser() {
      console.log("save");
      let params = {
        id: null,
        username: this.messageForm.username,
        password: this.messageForm.password,
        phone: this.messageForm.phone,
        email: this.messageForm.email,
        mnumber: this.messageForm.mnumber,
        medicine: this.messageForm.medicine,
        status: this.messageForm.status,
      };
      console.log(params);

      this.save("/user/", params, "messageVisible");
    },
    updateUser() {
      console.log("updateUser");
      let params = {
        uid: this.messageForm.id,
        username: this.messageForm.username,
        password: this.messageForm.password,
        phone: this.messageForm.phone,
        email: this.messageForm.email,
        mnumber: this.messageForm.mnumber,
        medicine: this.messageForm.medicine,
        status: this.messageForm.status,
      };
      console.log(params);
      this.update("/user/", params, "messageVisible");
    },
  },
};
</script>

<style scoped>
.main-login {
  height: 100%;
}

.danger-text {
  color: #f56c6c;
}
</style>
