<template>
<Goban
    :max-width="maxSize"
    :max-height="maxSize"
    :animate="true"
    :busy="isBusy"
    :range-x="showCorner ? [8, 18] : undefined"
    :range-y="showCorner ? [12, 18] : undefined"
    :coord-x="alternateCoordinates ? chineseCoordx : undefined"
    :coord-y="alternateCoordinates ? chineseCoordy : undefined"
    :sign-map="chessArray"
    :show-coordinates="showCoordinates"
    :fuzzy-stone-placement="fuzzyStonePlacement"
    :animate-stone-placement="animateStonePlacement"
    :paint-map="showPaintMap ? paintMap : undefined"
    :heat-map="showHeatMap ? heatMap : undefined"
    :marker-map="showMarkerMap ? markerMap : undefined"
    :ghost-stone-map="showGhostStones ? ghostStoneMap : undefined"
    :lines="showLines ? [
        {type: 'line', v1: [15, 6], v2: [12, 15]},
        {type: 'arrow', v1: [10, 4], v2: [5, 7]}
    ] : []"
    :dimmed-map="showDimmedStones ? dimmedMap : undefined"
    :selected-map="showSelection ? selectedMap : undefined"
    @click="onVertexClick"
    @rightClick="onRightClick"
    />
</template>

<script>
import Goban from './Shudan';
import {mapGetters, mapMutations, mapActions} from "vuex";




export default {
    name: 'ChessBoard',
    components: {
        Goban
    },

    data: function () {
        return {
            maxSize: 480,
            x: 0,
            y: 0,
            showCoordinates: false,
            alternateCoordinates: false,
            showCorner: false,
            showDimmedStones: false,
            fuzzyStonePlacement: false,
            animateStonePlacement: false,
            showPaintMap: false,
            showHeatMap: false,
            showMarkerMap: false,
            showGhostStones: false,
            showLines: false,
            showSelection: false,
            isBusy: false,

            checkedNames: [],

            // actionUrl: 'https://jsonplaceholder.typicode.com/posts/',
            actionUrl: 'http://127.0.0.1:5000/img/upload',
            goImgUrl: "",
            activeModule: 0,
            dataList: [[],[]],
            selectedItem: {},
            addLine: null,
            deleteLine: null,
            addColumn: null,
            deleteColumn: null
        };
    },




    props: {
        boardSize: {
            type: Number,
            default: 19
        },
    },

    created() {
        // let _this = this;
        // this.$http.get('http://127.0.0.1:5000/datas').then(function (res){
        //     console.log(res.data);
        //     _this.$set(_this.dataList, 0, res.data.ng);
        //     _this.$set(_this.dataList, 1, res.data.ok);
        // });
    },
    mounted() {
        /*
        let _this = this;
        this.$http.get('http://127.0.0.1:5000/get-chess', {
            params:{
                path:'C:/Users/admin/Desktop/python-space/GoChessRecognition/docs/10061.jpg'
            }
        }).then(function (res){
            console.log(res.data);
            _this.rawchessArray = res.data;
            _this.chessArray = res.data;
        });
        */

        // this.uploadList = this.$refs.upload.fileList;
    },

    methods: {
        ...mapMutations([

        ]),

        ...mapActions([
            'move',
        ]),

        onVertexClick: function (offsetX, offsetY) {
            this.x = offsetX;
            this.y = offsetY;
            if (this.gameState !== "Gaming") {
                this.$Message.info('Please Start first');
                return;
            }
            // let aConstMap = {'-1': 1, '0': Math.sign(Math.random() - 0.5) || 1, '1': -1};
            let chessArray = JSON.parse(JSON.stringify(this.chessArray));
            // chessArray[offsetX][offsetY] = Math.sign(Math.random() - 0.5) || 1;
            if (chessArray[offsetX][offsetY] !== 0) {
                this.$Message.info('Already have a chess piece here');
            } else {
                chessArray[offsetX][offsetY] = this.chessPiece;
                const data = {
                    chessPiece: this.chessPiece,
                    x: offsetX,
                    y: offsetY,
                };
                this.move(data);
                if (this.chessPiece === 1){
                    this.chessPiece = -1;
                } else {
                    this.chessPiece = 1;
                }
                this.chessArray = chessArray;
            }

        },
        onRightClick: function (offsetX, offsetY) {
            console.log(offsetX, offsetY);
            let chessArray = JSON.parse(JSON.stringify(this.chessArray));
            /*
            chessArray[offsetX][offsetY] = chessArray[offsetX][offsetY] + 1;
            chessArray[offsetX][offsetY] = chessArray[offsetX][offsetY] > 1 ? -1 : chessArray[offsetX][offsetY];
            */
            chessArray[offsetX][offsetY] = 0;
            this.chessArray = chessArray;
        },

        handleRetract() {
            let chessArray = JSON.parse(JSON.stringify(this.chessArray));
            /*
            chessArray[offsetX][offsetY] = chessArray[offsetX][offsetY] + 1;
            chessArray[offsetX][offsetY] = chessArray[offsetX][offsetY] > 1 ? -1 : chessArray[offsetX][offsetY];
            */
            chessArray[this.x][this.y] = 0;
            this.chessArray = chessArray;
            if (this.chessPiece === 1){
                this.chessPiece = -1;
            } else {
                this.chessPiece = 1;
            }

        },

        initializeBoard() {
            let chessArray = new Array(this.boardSize);
            this.chessPiece = 1;
            for (let i=0;i<chessArray.length; i++) {
                chessArray[i] = new Array(this.boardSize).fill(0);
            }
            this.chessArray = chessArray;
        },

        imgUploadHandleSuccess(res, file) {
            console.log(file);
            this.rawchessArray = res.go_data;
            this.chessArray = res.go_data;
            this.goImgUrl = res.base64_data;
        },
        handleView(file) {
            this.goImgUrl = file.response.base64_data;
        },
        handleRemove(file) {
            const fileList = this.$refs.upload.fileList;
            this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
        },
        handleSelectView(item) {
            let _this = this;
            const typeArr = ['ng', 'ok'];
            this.$http.get('http://127.0.0.1:5000/getAnnotation?type=' + typeArr[this.activeModule] + '&filename=' + item.name).then(function (res){
                console.log(res.data);
                _this.chessArray = res.data;
                _this.goImgUrl = item.base64;
                _this.selectedItem = item;
            });
        },
        handleAddLine() {
            this.chessArray.splice(this.addLine, 0, [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
        },
        handleDeleteLine() {
            this.chessArray.splice(this.deleteLine - 1, 1);
        },
        handleAddColumn() {
            const aamap = {'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'J': 8, 'K': 9, 'L': 10, 'M': 11, 'N': 12, 'O': 13, 'P': 14, 'Q': 15, 'R': 16, 'S': 17, 'T': 18, 'U': 19, 'V': 20, 'W': 21, 'X': 22, 'Y': 23, 'Z': 24};
            let addIndex = this.addColumn;
            var reg= /^[A-Za-z]/;
            if (reg.test(this.addColumn)){
                this.addColumn = this.addColumn.toUpperCase();
                addIndex = aamap[this.addColumn] + 1;
            }
            for (let index in this.chessArray) {
                let line = this.chessArray[index];
                line.splice(addIndex, 0, 0);
            }
        },
        handleDeleteColumn() {
            const aamap = {'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'J': 8, 'K': 9, 'L': 10, 'M': 11, 'N': 12, 'O': 13, 'P': 14, 'Q': 15, 'R': 16, 'S': 17, 'T': 18, 'U': 19, 'V': 20, 'W': 21, 'X': 22, 'Y': 23, 'Z': 24};
            let deleteIndex = this.deleteColumn;
            var reg= /^[A-Za-z]/;
            if (reg.test(this.deleteColumn)){
                this.deleteColumn = this.deleteColumn.toUpperCase();
                deleteIndex = aamap[this.deleteColumn] + 1;
            }
            for (let index in this.chessArray) {
                let line = this.chessArray[index];
                line.splice(deleteIndex - 1, 1);
            }
        },
        copyGoData() {
            let goData = JSON.stringify(this.chessArray);
            // goData = goData.replace(/,/g, ', ');
            // goData = goData.replace(/], /g, '],\n');
            goData = goData.replace(/],/g, ']\n');
            goData = goData.replace(/,/g, '  ');
            goData = goData.replace(/\[/g, ' [ ');
            goData = goData.replace(/ \[ {2}\[/g, '[[');
            goData = goData.replace(/ -1/g, '-1');
            
            console.log(goData, this.selectedItem.name, this.activeModule);
            console.log(goData.length);
            console.log(goData.split("\n").length - 1);

            // let _this = this;
            if (goData.length != 1140 || (goData.split("\n").length - 1) != 18) {
                this.$Message.error('棋盘形状异常，请检查！');
            } else {
                const typeArr = ['ng', 'ok'];
                this.$http.get('http://127.0.0.1:5000/save?type=' + typeArr[this.activeModule] + '&filename=' + this.selectedItem.name + '&goData=' + goData).then(function (res){
                    console.log(res.data);
                    this.$Message.success('保存成功：' + res.data);
                    if (this.activeModule == 0) {
                        this.dataList[0].splice(this.dataList[0].indexOf(this.selectedItem), 1);
                        this.dataList[1].push(this.selectedItem);
                    }
                });
            }
        }
    },

    computed: {
        ...mapGetters([
            "gameState"
        ]),

        chessArray: {
            get() {
                return this.$store.state.chessBoard.chessArray;
            },
            set(val) {
                return this.$store.state.chessBoard.chessArray = val;
            }
        },

        checkBoxs: function () {
            return [
                { stateKey: 'showCoordinates', text: 'Show coordinates' },
                { stateKey: 'alternateCoordinates', text: 'Alternate coordinates' },
                { stateKey: 'showCorner', text: 'Show lower right corner only' },
                { stateKey: 'showDimmedStones', text: 'Dim dead stones' },
                { stateKey: 'fuzzyStonePlacement', text: 'Fuzzy stone placement' },
                { stateKey: 'animateStonePlacement', text: 'Animate stone placement' },
                { stateKey: 'showMarkerMap', text: 'Show markers' },
                { stateKey: 'showGhostStones', text: 'Show ghost stones' },
                { stateKey: 'showPaintMap', text: 'Show paint map' },
                { stateKey: 'showHeatMap', text: 'Show heat map' },
                { stateKey: 'showLines', text: 'Show lines' },
                { stateKey: 'showSelection', text: 'Show selection' },
                { stateKey: 'isBusy', text: 'Busy' }
            ];
        },
        uploadList() {
            return this.dataList[this.activeModule];
        }
    },

    watch: {
        checkedNames: function () {
            let { checkBoxs, checkedNames } = this;
            checkBoxs.map((value) => {
                let { stateKey } = value;
                let newState = checkedNames.indexOf(stateKey) > -1 ? true : false;
                if (this[stateKey] != newState)
                    this[stateKey] = newState;
            });
        },

        boardSize: {
            handler(newVal) {
                let chessArray = new Array(newVal);
                for (let i=0;i<chessArray.length; i++) {
                    chessArray[i] = new Array(newVal).fill(0);
                }
                this.chessArray = chessArray;
            },
            deep: false,
            immediate: false,
        },
    }
};
</script>


<style>
body {
    font-family: "Segoe UI", Ubuntu, Helvetica, Arial, sans-serif;
}
.demo-upload-list{
    display: inline-block;
    width: 60px;
    height: 60px;
    text-align: center;
    line-height: 60px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0,0,0,.2);
    margin-right: 4px;
}
.demo-upload-list img{
    width: 100%;
    height: 100%;
}
.demo-upload-list-cover{
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0,0,0,.6);
}
.demo-upload-list:hover .demo-upload-list-cover{
    display: block;
}
.demo-upload-list-cover i{
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
}
</style>
