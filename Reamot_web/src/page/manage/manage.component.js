export default {
    data() {
        return {
            appOptions: [],
            appValue: '',
        }
    },
    created() {
        // this.getApp();
    },
    methods: {
        save(url, params, visible) {
            url = "http://localhost:9999"+ url;
            this.$http.post(url, params).then(({body}) => {
                console.log(body);
                if (body.success === true) {
                    this.$message.success(body.message);
                    this.queryList();
                    if (visible !== '') {
                        this[visible] = false;
                    }
                } else this.$message.error(body.message);
            }).catch(() => {
                this.$message.error('操作失败');
            })
        },
        update(url, params, visible) {
            url = "http://localhost:9999"+ url;
            this.$http.put(url, params).then(({body}) => {
                console.log(body);
                if (body.success === true) {
                    this.$message.success(body.message);
                    this.queryList();
                    if (visible !== '') {
                        this[visible] = false;
                    }
                } else this.$message.error(body.message);
            }).catch(() => {
                this.$message.error('操作失败');
            })
        },
        handleSizeChange(val) {
            this.pagination.pageSize = val;
            this.pagination.pageIndex = 1;
            this.queryList();
        },
        handleCurrentChange(val) {
            this.pagination.pageIndex = val;
            this.queryList();
        },
        delete(url) {
            url = "http://localhost:9999"+ url;

            this.$confirm('The information cannot be recovered after deletion, are you sure you want to delete it？', 'tips', {
                confirmButtonText: 'determine',
                cancelButtonText: 'cancel',
                type: 'warning',
            }).then(() => {
                this.$http.delete(url, {}, ).then(({body}) => {
                    if (body.success === true) {
                        this.$message.success(body.message);
                        this.queryList();
                    } else {
                        this.$message.error(body.message);
                    }
                }).catch(() => {
                    this.$message.error('删除失败');
                })
            }).catch(() => {
                this.$message.info('已取消删除')
            })
        },
    }
}
