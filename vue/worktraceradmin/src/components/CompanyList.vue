<template>
    <div>
        <el-table stripe="true" :data="tableData" style="width: 90%; margin: 10px">
            <el-table-column prop="id" label="id">

            </el-table-column>
            <el-table-column prop="companyName" label="name">

            </el-table-column>
            <el-table-column prop="workshop" label="workshop">

            </el-table-column>
            <el-table-column prop="submitTime" label="time">

            </el-table-column>
            <el-table-column label="options" fixed="right">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="approveCom(scope.row.id)">
                        通过
                    </el-button>
                    <el-button type="text" size="small" @click="declineCom(scope.row.id)">
                        退回
                    </el-button>
                </template>

            </el-table-column>
        </el-table>
        <el-pagination
                layout="prev, pager, next" background
                :pager-count="5" :current-page="1" :page-count="pageCount" @current-change="pageChange">
        </el-pagination>
    </div>

</template>

<script>

    export default {
        name: "CompanyList",
        data(){
            return {
                tableData :[
                    {

                    }
                ],
                pageCount :5,
                page:0,
            }
        },
        methods:{
            approveCom(id){
                let jsonData = {
                    companyId:id
                };
                const that = this;
                this.$http.post('/admin-approve',jsonData).then(function (response) {
                    if(!response.data.errCode){
                        that.$message({
                            message:"ok",
                        })
                    }
                    that.getCompanyList(that.page,50);
                }).catch(function (error) {
                    that.$message({message:error.response.message, type:"warning"})
                })
            },
            declineCom(id){
                let jsonData = {
                    companyId:id
                };
                const that = this;
                this.$http.post('/admin-decline',jsonData).then(function (response) {
                    if(!response.data.errCode){
                        that.$message({
                            message:"ok",
                        })
                    }
                    that.getCompanyList(that.page,50);
                }).catch(function (error) {
                    that.$message({message:error.response.message, type:"warning"})
                })
            },
            pageChange(current){
                let page = current-1;
                this.page=page;
                this.getCompanyList(page, 50);
            },
            getCompanyList(page, size){
                  let jsonData = {
                      search:"",
                      page:page,
                      size:size,
                  };
                  const that = this;
                  this.$http.post('/admin-query-unapproved', jsonData).then(function (response) {
                        if(!response.data.errCode){
                            that.tableData = response.data.data;
                            if(that.tableData.length < 50){
                                that.pageCount = page+1;
                            }else{
                                that.pageCount = page+5;
                            }

                        }
                  }).catch(function (error) {
                        that.$message({message:error.response.message, type:"warning"})
                  })
          },
        },
        mounted() {
            this.getCompanyList(0,50);
        }
    }
</script>

<style scoped>

</style>