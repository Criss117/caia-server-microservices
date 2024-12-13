"use client";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { SignUpDto } from "../models/types";
import { SignUpSchema } from "../models/schemas";
import { signUp } from "../../data/actions/signup.action";
import { useRouter } from "next/navigation";
import { ROUTES } from "@/core/shared/lib/constants/routes";

const defaultValues: SignUpDto = {
  firstName: "andres",
  lastName: "castillo",
  email: "andres@email.com",
  password: "holamundo",
  affiliation: "alguna afiliacion",
};

const useSignUp = () => {
  const router = useRouter();
  const form = useForm<SignUpDto>({
    resolver: zodResolver(SignUpSchema),
    defaultValues,
  });

  const onSubmit = form.handleSubmit(async (signUpDto) => {
    form.clearErrors();
    const response = await signUp(signUpDto);

    if (response.status !== 200) {
      form.setError("root", { message: response.message });
      return;
    }

    router.push(ROUTES.ACCOUNT_CREATED);
  });

  return {
    form,
    onSubmit,
  };
};

export default useSignUp;
