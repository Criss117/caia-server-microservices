import { useEffect, useState } from "react";

const usePaperKeys = () => {
  const [key, setKey] = useState<string | null>(null);
  const [keys, setKeys] = useState<string[]>([]);

  const addKey = (key: string) => {
    setKeys((prevKeys) => [...prevKeys, key]);
  };

  const initialKeys = (keys: string) => {
    setKeys(keys.split(","));
  };

  useEffect(() => {
    if (
      key?.includes(" ") ||
      key?.includes("\n") ||
      key?.includes("\t") ||
      key?.includes("\r") ||
      key?.includes(",")
    ) {
      addKey(key.slice(0, -1));
      setKey(null);
    }
  }, [key]);

  return {
    key,
    keys,
    setKey,
    addKey,
    initialKeys,
  };
};

export default usePaperKeys;
