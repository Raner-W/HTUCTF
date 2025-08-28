import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // 导入路由配置

// 正确的方式：创建应用实例并使用路由
const app = createApp(App)
app.use(router)
app.mount('#app')