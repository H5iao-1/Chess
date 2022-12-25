<template>
<Layout style="position: absolute;height: 100%; width: 100%">
    <Header style="height: 10%">Header</Header>
    <Layout style="height: 80%">
        <Content>
            <ChessBoard
                style="margin: auto"
                :board-size="boardSize"
                ref = "chessBoard"/>
        </Content>
    </Layout>
    <Footer style="height: 10%">
        <Select v-model="boardSize" style="width:200px">
            <Option v-for="item in sizeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
        <Select v-model="gameMode" style="width:200px">
            <Option v-for="item in modeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
        <Button type="primary" shape="circle" @click="handleStart">Start</Button>
        <Button type="primary" shape="circle" @click="handleEnd">End</Button>
        <Button type="primary" shape="circle" @click="handleRetract">Retract</Button>
    </Footer>
</Layout>
</template>

<script>
import ChessBoard from "../components/ChessBoard";
import { mapGetters, mapMutations, mapActions } from 'vuex';

export default {
    name: "Game",
    components: {
        ChessBoard,
    },
    data: function (){
        return {
            boardSize: 19,
            sizeList: [
                {
                    value: 8,
                    label: '8'
                },
                {
                    value: 9,
                    label: '9'
                },
                {
                    value: 10,
                    label: '10'
                },{
                    value: 11,
                    label: '11'
                },{
                    value: 12,
                    label: '12'
                },{
                    value: 13,
                    label: '13'
                },{
                    value: 14,
                    label: '14'
                },{
                    value: 15,
                    label: '15'
                },{
                    value: 16,
                    label: '16'
                },{
                    value: 17,
                    label: '17'
                },{
                    value: 18,
                    label: '18'
                },{
                    value: 19,
                    label: '19'
                },
            ],
            modeList: [
                {
                    value: "Gobang",
                    label: 'Gobang'
                },
                {
                    value: 'Go',
                    label: 'Go'
                },],
        };
    },
    computed: {
        ...mapGetters([

        ]),

        gameMode: {
            get() {
                return this.$store.state.chessBoard.gameMode;
            },
            set(val) {
                return this.$store.state.chessBoard.gameMode = val;
            }
        },

        gameState: {
            get() {
                return this.$store.state.chessBoard.gameState;
            },
            set(val) {
                return this.$store.state.chessBoard.gameState = val;
            }
        },

    },

    methods: {
        ...mapMutations([

        ]),
        ...mapActions([
            'startGame',
            'endGame'
        ]),

        handleStart() {
            this.$refs.chessBoard.initializeBoard();

            this.endGame();
            const data = {
                gameMode: this.gameMode,
                boardSize: this.boardSize
            };

            this.gameState = "Gaming";
            this.startGame(data);
        },

        handleEnd() {
            this.endGame();
            this.$refs.chessBoard.initializeBoard();
            this.gameState = '';
        },

        handleRetract() {
            this.$refs.chessBoard.handleRetract();
        }
    }
};
</script>

<style scoped>

</style>