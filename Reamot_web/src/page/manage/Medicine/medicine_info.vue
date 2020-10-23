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
          @click="createMedicine"
          slot="reference"
          >Add</el-button
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
                @click="modifyMedicine(scope.row.id)"
                >Modify
              </el-button>
              <el-button
                type="text"
                size="mini"
                class="danger-text"
                @click="deleteMedicine(scope.row.id)"
                >Delete
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </lyz-layout>
    <el-dialog
      :title="'Add medicine information'"
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
        <el-form-item label="name" prop="name">
          <el-input
            v-model="messageForm.name"
            placeholder="Please enter medicine name"
          ></el-input>
        </el-form-item>

        <el-form-item label="info" prop="info">
          <el-input
            v-model="messageForm.info"
            placeholder="Please enter medicine info"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="messageVisible = false">Cancel</el-button>

        <el-button type="primary" @click="updateMedicine" v-if="isModify"
          >Update</el-button
        >
        <el-button type="primary" @click="saveMedicine" v-else>Add</el-button>
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
  name: "Medicine",
  data() {
    return {
      queryType: "",
      queryKeyword: "",
      pagination: {
        pageIndex: 1,
        pageSize: 10,
        total: 0,
      },
      label: "Medicine information management",
      messageForm: {},
      messageVisible: false,
      messageLabelWidth: "90px",
      isModify: false,
      messageRule: {
        name: [{ required: true, message: "please enter medicine name", trigger: "blur" }],
        info: [{ required: true, message: "please enter medicine info", trigger: "blur" }],
      },
      multipleSelection: [], //多选的数据
      pickerOptions: {},
      tableData: [],
      loginLoading: false,
      tableHeader: [
        {
          prop: "id",
          label: "ID",
          "min-width": 40,
          align: "center",
        },
        {
          prop: "name",
          label: "name",
          "min-width": 60,
          align: "center",
        },
        {
          prop: "info",
          label: "info",
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
        .get("http://localhost:9999/medicine", { params: params })
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
    deleteMedicine(id) {
      console.log("delete"+id)
      this.delete("/medicine/" + id);
    },
    modifyMedicine(id) {
      console.log("mofify");
      this.isModify = true;
      this.$http
        .get("http://localhost:9999/medicine/" + id)
        .then(({ body }) => {
          if (body.success === true) {
            let params = {
              id: id,
              name: body.data.name,
              info: body.data.info,
            };

            this.messageVisible = true;
            let _form = Object.assign({}, id);

            _form.id = params.id;
            _form.name = params.name;
            _form.info = params.info;

            this.messageForm = _form;
          } else {
            this.$message.error(body.message);
          }
        })
        .finally(() => {
          this.loginLoading = false;
        });
    },
    createMedicine(row) {
      console.log("create");
      this.isModify = false;
      console.log(row);
      this.messageVisible = true;
      let _form = Object.assign({}, row);
      this.messageForm = _form;
    },
    saveMedicine() {
      console.log("save");
      let params = {
        id: null,
        name: this.messageForm.name,
        info: this.messageForm.info,
      };
      console.log(params);

      this.save("/medicine/", params, "messageVisible");
    },
    updateMedicine() {
      console.log("updateMedicine");
      let params = {
        id: this.messageForm.id,
        name: this.messageForm.name,
        info: this.messageForm.info,
      };
      console.log(params);
      this.update("/medicine/", params, "messageVisible");
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
