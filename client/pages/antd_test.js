import { Button } from "antd";
import { Rate } from "antd";
import { FrownOutlined, MehOutlined, SmileOutlined } from "@ant-design/icons";

const customIcons = {
  1: <FrownOutlined />,
  2: <FrownOutlined />,
  3: <MehOutlined />,
  4: <SmileOutlined />,
  5: <SmileOutlined />,
};

function antdTest() {
  return (
    <div className="antd-test-wrap">
      <div className="rate-wrap">
        <Rate defaultValue={2} character={({ index }) => index + 1} />
        <br />
        <Rate
          defaultValue={3}
          character={({ index }) => customIcons[index + 1]}
        />
      </div>

      <Button type="primary">Button</Button>
    </div>
  );
}
export default antdTest;
