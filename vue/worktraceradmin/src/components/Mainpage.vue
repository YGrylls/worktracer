<template>
    <el-container>
        <el-header id="header" height="80px">
            <div id = "title">
                WorkTracer Admin
            </div>
        </el-header>
        <el-main>
            <el-col :span="4">
                <el-menu class="menu"
                default-active="1">
                    <el-menu-item style="padding-left: 0" index="1">
                        <i class="el-icon-view"></i>
                        Company 审核
                    </el-menu-item>
                </el-menu>
            </el-col>
            <el-col :span="20">
                <CompanyList></CompanyList>
            </el-col>


        </el-main>
    </el-container>
</template>

<script>
    import CompanyList from "./CompanyList";
    export default {
        name: "Mainpage",
        components: {CompanyList}
    }
</script>

<style scoped>
    #header {
        background-color:deepskyblue;
    }

    #title {
        color: white;
        font-family: "Calibri",serif;
        font-size: 60px;
        text-align: left;
    }

    .menu{
        text-align: left;
        padding-left: 0;
    }
</style>