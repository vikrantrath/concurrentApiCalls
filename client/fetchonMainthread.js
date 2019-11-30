const { Worker, isMainThread, parentPort } = require('worker_threads');
const myWorker = new Worker('fetchonbackground.js');
const res = myWorker.postMessage("Hey")
console.log(res)