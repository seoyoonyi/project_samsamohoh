import React, { useState } from "react";
import { Link } from "react-router-dom"

const Tag = () => {
  return (
    <>
        <div>
            <button>
              <Link to="/">최신</Link>
            </button>
        </div>
        <div>
            <button>
              <Link to="/popul">인기</Link>
            </button>
        </div>
    </>
  )
};

  export default Tag;