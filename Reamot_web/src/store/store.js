const STORAGE_KEY = 'todos-vuejs'
export default {
    fetch() {
        return window.localStorage.getItem(STORAGE_KEY)
    },
    save(items) {
        window.localStorage.setItem(STORAGE_KEY, items)
    }
}
