import { useState } from "react";
import { useRouter } from "next/navigation";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { signIn } from "next-auth/react";

import { LoginDto } from "../models/types";
import { LoginSchema } from "../models/schemas";
import { ROUTES } from "@/core/shared/lib/constants/routes";

const defaultValues: LoginDto = {
  email: "cristian@email.com",
  password: "holamundo",
};

const useLogin = () => {
  const router = useRouter();
  const [pending, setPending] = useState(false);

  const form = useForm<LoginDto>({
    resolver: zodResolver(LoginSchema),
    defaultValues,
  });

  const onSubmit = form.handleSubmit(async (loginDto) => {
    form.clearErrors();

    setPending(true);
    const res = await signIn("credentials", {
      email: loginDto.email,
      password: loginDto.password,
      redirect: false,
    });

    if (res?.error && res.code === "credentials") {
      form.setError("root", {
        message: "Credenciales inválidas",
      });

      setPending(false);
      return;
    }

    router.replace(ROUTES.DASHBOARD.OWN);
    setPending(false);
  });

  return {
    form,
    pending,
    onSubmit,
  };
};

export default useLogin;
