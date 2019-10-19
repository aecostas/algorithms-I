const UF = require ('./UF');
const UFLazy = require ('./UFLazy');

size = 1000000;
uf = new UFLazy(size);

console.time();

for (let i=2; i < size; i+=2) {
    uf.union(i, i-2);
}


for (let i=3; i < size; i+=2) {
    uf.union(i,i-2);
}

console.timeEnd()

