class UF {

    constructor(N) {
        this.N = N;
        // no connections at the beginning
        this.ids = Array.from({length: N}, (_, i) => i);
    }

    union(p, q) {
        let pID = this.ids[p];
        let qID = this.ids[q];

        for (let i = 0; i<this.ids.length; i++) {
            if (this.ids[i] === pID) {
                this.ids[i] = qID;
            }
        }
    }

    connected(p, q) {
        return ids[p] === ids[q];
    }

    debug(){
        console.log(this.ids);
    }
}

module.exports = UF;