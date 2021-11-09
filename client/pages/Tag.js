import React, { useState } from "react";

const Tag = () => {
    const [content, setContent] = useState(false);

    const latest = () => {
        setContent();
    }

    const popul = () => {
        setContent();
    }

  return (
    <>
        <div>
            <button onClick={() => setContent(!content)}>최신</button>
            {content && (<div>최신내용</div>)}
        </div>
        <div>
            <button onClick={() => setContent(!content)}>인기</button>
            {content && (<div>인기내용</div>)}
        </div>
    </>
  );
};

export default Tag;