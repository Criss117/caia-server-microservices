"use client";

import { useRouter } from "next/navigation";
import { Button } from "./button";
import { cn } from "../../lib/utils";

interface Props {
  className?: string;
}

const BackButton = ({ className }: Props) => {
  const router = useRouter();

  return (
    <Button onClick={() => router.back()} className={cn(className)}>
      Atras
    </Button>
  );
};

export default BackButton;
