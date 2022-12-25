<template>
<div id="app" v-title data-title="Chess">
    <transition name="fade-transform" mode="out-in">
        <router-view />
    </transition>
</div>
</template>

<script>
export default {
    name: "App",
    components: {

    }
};
</script>

<style scoped>

</style>