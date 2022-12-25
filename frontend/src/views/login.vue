<template>
<Layout style="position: absolute;height: 100%;width: 100%">
    <Header style="height: 15%;background-color: rgb(245, 248, 255)"></Header>
    <Header style="height: 10%;background-color: rgb(245, 248, 255)">
        <div style="text-align: center;">
            <span class="title">CHESS Platform</span>
        </div>
    </Header>
    <Layout style="height: 80%">
        <div style="width: 100%; margin-top: 1%">
            <div style=" margin: auto;height: 70%;width: 20%;" class="tabs-style form-style">
                <Tabs value="login">
                    <TabPane label="Sign in" name="login">
                        <Form ref="formData" :model="formData" :rules="ruleValidate">
                            <FormItem prop="username">
                                <Input type="text" v-model="formData.username" placeholder="Username">
                                <Icon type="ios-person-outline" slot="prepend"></Icon>
                                </Input>
                            </FormItem>
                            <FormItem prop="password">
                                <Input type="password" v-model="formData.password" placeholder="Password">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                </Input>
                            </FormItem>
                            <FormItem>
                                <Button type="primary" @click="handleSignIn(formData)">Sign In</Button>
                            </FormItem>
                        </Form>
                    </TabPane>
                    <TabPane label="Sign up" name="register">
                        <Form ref="ruleValidate" :model="formData" :rules="ruleValidate">
                            <FormItem prop="username">
                                <Input type="text" v-model="formData.username" placeholder="Username">
                                <Icon type="ios-person-outline" slot="prepend"></Icon>
                                </Input>
                            </FormItem>
                            <FormItem prop="password">
                                <Input type="password" v-model="formData.password" placeholder="Password">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                </Input>
                            </FormItem>
                            <FormItem>
                                <Button type="primary" @click="handleSignUp('formData')">Sign Up</Button>
                            </FormItem>
                        </Form>
                    </TabPane>
                </Tabs>
            </div>
        </div>
    </Layout>
</Layout>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex';

export default {
    name: "Login",

    data: function (){
        return {
            formData: {
                username: "",
                password: ""
            },
            ruleValidate: {
                username: [
                    { required: true, message: 'The username cannot be empty', trigger: 'blur' }
                ],
                password: [
                    { required: true, min: 8, message: 'Password is too short', trigger: 'blur' }
                ],
            }
        };
    },
    methods: {

        ...mapActions([
            'login',
            'register',
        ]),

        handleSignIn() {
            const data = {
                username: this.formData.username,
                password: this.formData.password
            };
            this.$refs.formData.validate(async(valid)  => {
                if (valid) {
                    await this.login(data);
                } else {
                    this.$Message.error('Fail!');
                }
            });
        },


        handleSignUp() {
            const data = {
                username: this.formData.username,
                password: this.formData.password
            };
            this.$refs.formData.validate(async(valid) => {
                if (valid) {
                    await this.register(data);
                } else {
                    this.$Message.error('Fail!');
                }
            });

        },

    }
};
</script>

<style scoped>
.title {
    font-size: 33px;
    color: black;
    font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
    font-weight: 600;
}

.tabs-style >>> .ivu-tabs-nav {
    width: 100%;
    /*background-color: red;*/
}

.tabs-style >>> .ivu-tabs-tab {
    width: 50%;
    margin: 0;
    text-align: center;
    font-size: 20px;
}

.form-style >>> .ivu-btn {
    width: 100%
}


</style>