class UFLazy {

    constructor(N) {
        this.N = N;
        // no connections at the beginning
        this.ids = Array.from({length: N}, (_, i) => i);
    }

    _root(i) {
        while (this.ids[i] !== i) {
            //this.ids[i] = this.ids[this.ids[i]]; // path compression
            i = this.ids[i]
        }

        return i;
    }

    union(p, q) {
      this.ids[this._root(p)] = this._root(q);
    }

    connected(p, q) {
        return this._root(p) === this._root(q);
    }

    debug() {
        console.log(this.ids)
    }
}

module.exports = UFLazy;