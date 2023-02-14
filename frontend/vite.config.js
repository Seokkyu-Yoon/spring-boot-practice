import { resolve } from 'node:path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'


const inputMap = new Map()
function addInput (dirpath = null) {
  const key = dirpath || 'main'
  if (inputMap.has(key)) throw new Error(`Named page '${key}' is already exists`)
  inputMap.set(key, resolve(...[
    __dirname,
    ...(dirpath === null ? [] : [dirpath]),
    'index.html'
  ]))
}
addInput()
addInput('about')

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    {
      name: 'html-render',
      transformIndexHtml(html) {
        return html.replace()
      }
    }
  ],
  server: {
    proxy: {
      '/api': 'http://localhost:12480'
    },
  },
  build: {
    sourcemap: true,
    outDir: resolve(__dirname, '..', 'demo', 'src', 'main', 'resources', 'static'),
    // rollupOptions: {
    //   input: Object.fromEntries(inputMap)
    // },
    emptyOutDir: true
  }
})

