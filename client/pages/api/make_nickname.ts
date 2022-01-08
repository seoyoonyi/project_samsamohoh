import type { NextApiRequest, NextApiResponse } from "next";
import fetcher from "../../common/fetcher";

const handler = async (_req: NextApiRequest, res: NextApiResponse) => {
  const minNum = 6;
  const maxNum = 9;
  const ramdomNum = Math.floor(Math.random() * (maxNum - minNum) + minNum);
  const paramNum = ramdomNum.toString();

  let result = await fetcher(
    "get",
    `https://nickname.hwanmoo.kr/?format=json&count=1&max_length=${paramNum}&whitespace=_`
  );

  result = result.words[0].replace("_", "");

  res.status(200).json({ data: result });
};

export default handler;
