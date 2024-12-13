"use client";

import { HTMLInputTypeAttribute } from "react";
import { Input } from "../ui/input";
import {
  FormControl,
  FormItem,
  FormMessage,
} from "@/core/shared/components/ui/form";
import { cn } from "../../lib/utils";

interface Props {
  label: string;
  placeholder?: string;
  type: HTMLInputTypeAttribute | undefined;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  field: any;
  hidden?: boolean;
  className?: string;
  min?: number;
  max?: number;
}

const FormItemInput = ({
  placeholder,
  type,
  field,
  hidden = false,
  className,
  min,
  max,
}: Props) => {
  return (
    <FormItem hidden={hidden} className={cn(className)}>
      <FormControl>
        <Input
          placeholder={placeholder}
          {...field}
          type={type}
          min={min}
          max={max}
        />
      </FormControl>
      <FormMessage className="bg-destructive/20 border-l-4 border-destructive" />
    </FormItem>
  );
};

export default FormItemInput;
