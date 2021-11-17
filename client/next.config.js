const path = require("path");

module.exports = {
  async rewrites() {
    if (process.env.NODE_ENV !== "production") {
      return [
        {
          destination: "http://13.59.1.176:8090",
          source: "/",
        },
      ];
    }
  },
  sassOptions: {
    includePaths: [path.resolve(__dirname, "./pages")],
  },
  reactStrictMode: true,
};
