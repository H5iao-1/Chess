import Vue from 'vue';
import router from '@/router';
import { getToken, setToken, removeToken } from '@/utils/auth';
import { resetRouter } from '@/router';
import { Message } from 'view-design';
import {loginAPI, registerAPI} from "@/api/user";

const user = {
    state: {
        userId: '',
    },

    mutations: {
        set_userId: (state, data) => {
            state.userId = data;
        },

    },

    actions: {
        login: async({ dispatch, commit }, userData) => {
            console.log(userData);
            const res = await loginAPI(userData);

            if(res){
                console.log(res.id);
                setToken(res);
                commit('set_userId', res.id);
                router.push('/');
            }
        },

        register: async({ commit }, data) => {
            const res = await registerAPI(data);
            if(res){
                commit('set_userId', res.id);
            }
        },
    },
};

export default user;

