import fs from 'node:fs'
import { resolve } from 'node:path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

const pageMap = new Map()
;(function readdir (relativePath = null) {
  const absolutePath = resolve(...[
    __dirname,
    ...(relativePath === null ? [] : [relativePath])
  ])
  const name = relativePath || 'main'
  const url = `/${relativePath || ''}`
  const targets = fs.readdirSync(absolutePath)
  targets.forEach(target => {
    const targetAbsolutePath = resolve(absolutePath, target)
    const targetRelativePath = [
      ...(relativePath === null ? [] : [relativePath]),
      target
    ].join('/')

    const stats = fs.statSync(targetAbsolutePath)
    if (stats.isDirectory()) return readdir(targetRelativePath)

    if (target === 'index.html') {
      if (pageMap.has(name)) throw new Error(`Named page ${name} is already exists`)
      pageMap.set(name, {
        url,
        relativePath: targetRelativePath,
      })
    }
  })
})()
console.log(pageMap)

const port = 8421
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue()
  ],
  server: {
    port,
    proxy: {
      '/api': 'http://localhost:12480',
      ...Array.from(pageMap).reduce((bucket, [name, { url, relativePath }]) => {
        const target = `http://localhost:${port}`
        const rewrite = (path) => relativePath
        bucket[`^${url}$`] = {
          target,
          rewrite
        }
        return bucket
      }, {})
    },
  },
  build: {
    sourcemap: true,
    outDir: resolve(__dirname, '..', 'demo', 'src', 'main', 'resources', 'static'),
    rollupOptions: {
      input: Array.from(pageMap.entries()).reduce((bucket, [name, { relativePath }]) => {
        bucket[name] = resolve(__dirname, relativePath)
        return bucket
      }, {})
    },
    emptyOutDir: true
  }
})
